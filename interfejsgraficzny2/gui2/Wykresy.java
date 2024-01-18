package interfejsgraficzny2.gui2;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import sklep.Polka;
import sklep.Regal;
import sklep.Sklep;
import statystyka.StatystykaTygodniowa;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Wykresy {
    public static void pokazRamkeSprzedazyCalorocznej(Sklep sklep) {
        DefaultCategoryDataset dataset = prepareDatasetSklep(sklep);
        JFreeChart chart = createChart(dataset);
        wyswietlWykres(chart, dataset);
    }



    public static void pokazWykresSprzedazyCalorocznejDlaRegalu(Regal regal) {
        DefaultCategoryDataset dataset = prepareDataset(regal);
        JFreeChart chart = createChart(dataset);
        wyswietlWykres(chart, dataset);
    }

    public static void pokazWykresSprzedazyTygodniowejDlaRegalu(Regal regal) {
        DefaultCategoryDataset dataset = prepareDataset(regal.getStatystykaTygodniowa());
        JFreeChart chart = createChart(dataset);
        wyswietlWykres(chart, dataset);
    }

    public static DefaultCategoryDataset prepareDatasetSklep(Sklep sklep) {
        Map<String, Double> dataMap = new HashMap<>();

        for (Regal regal : sklep.getRegalyWSklepie()) {
            dataMap.putAll(regal.getStatystykaCaloroczna().getStatystykiOgolne());
        }

        List<Map.Entry<String, Double>> entryList = new ArrayList<>(dataMap.entrySet());

        entryList.sort(Comparator.comparingDouble(Map.Entry::getValue));

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<String, Double> entry : entryList) {
            String key = entry.getKey();
            Double value = entry.getValue();
            dataset.addValue(value, "Przychód ze sprzedaży produktu", key);
        }

        return dataset;
    }

    private static DefaultCategoryDataset prepareDataset(Regal regal) {
        Map<String, Double> dataMap = new HashMap<>(regal.getStatystykaCaloroczna().getStatystykiOgolne());

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (String key : dataMap.keySet()) {
            dataset.addValue(dataMap.get(key), "Przychód ze sprzedaży produktu", key);
        }

        return dataset;
    }

    private static DefaultCategoryDataset prepareDataset(StatystykaTygodniowa statystykaTygodniowa) {
        Map<Polka, StatystykaTygodniowa.WartosciSprzedazy> dataMap = new TreeMap<>(statystykaTygodniowa.getWynikSprzedazyProduktu());

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Polka key : dataMap.keySet()) {
            dataset.addValue(dataMap.get(key).getZysk(), "Przychód ze sprzedaży produktu", key.getTypProduktu() + " - " + key.getProducent());
        }

        return dataset;
    }



    public static JFreeChart createChart(DefaultCategoryDataset dataset) {
        return ChartFactory.createBarChart(
                "Przychód ze sprzedaży poszczególnych produktów w ujęciu całorocznym",
                "Nazwa produktu",
                "Wartość",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
    }


    public static void wyswietlWykres(JFreeChart chart, DefaultCategoryDataset dataset) {
        CategoryPlot plot = chart.getCategoryPlot();
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // Create a table
            JTable table = createTable(dataset);

            JMenuBar menuBar = new JMenuBar();

            JMenuItem showTableInMenu = new JMenuItem("Wyswietl dane");
            showTableInMenu.addActionListener(e -> pokazTabele(table));
            menuBar.add(showTableInMenu);

            frame.setJMenuBar(menuBar);

            // Create a chart panel
            ChartPanel chartPanel = new ChartPanel(chart);

            // Use a split pane to display the chart on the left and the table on the right
            frame.add(chartPanel, BorderLayout.CENTER);

            // Set the size of the frame
            frame.setSize(650, 500);

            // Set the location of the frame
            frame.setLocationRelativeTo(null);

            frame.setVisible(true);
        });
    }

    private static JTable createTable(DefaultCategoryDataset dataset) {
        // Extract data from the dataset and create a table model
        List<?> rowKeys = dataset.getRowKeys();
        List<?> columnKeys = dataset.getColumnKeys();

        // Create a new two-dimensional array for pivoted data
        Object[][] pivotedData = new Object[columnKeys.size()][rowKeys.size() + 1];

        // Fill the first column with column names
        for (int i = 0; i < columnKeys.size(); i++) {
            pivotedData[i][0] = columnKeys.get(i);
        }

        // Fill the second column with rounded values from the second row of the original table
        for (int i = 0; i < rowKeys.size(); i++) {
            for (int j = 0; j < columnKeys.size(); j++) {
                // Round the value to two decimal places
                pivotedData[j][i + 1] = roundToTwoDecimalPlaces(dataset.getValue((Comparable<?>) rowKeys.get(i), (Comparable<?>) columnKeys.get(j)));
            }
        }

        // Create column names for the table
        Object[] columnNames = new Object[rowKeys.size() + 1];
        columnNames[0] = "Produkt";
        for (int i = 0; i < rowKeys.size(); i++) {
            columnNames[i + 1] = rowKeys.get(i);
        }

        // Create a table model
        DefaultTableModel tableModel = new DefaultTableModel(pivotedData, columnNames);

        // Create and return the table
        return new JTable(tableModel);
    }
    private static void pokazTabele(JTable table) {
        // Pokaż tabelę w nowym oknie
        JFrame tableFrame = new JFrame("Dane przychodu");
        tableFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JScrollPane tableScrollPane = new JScrollPane(table);
        tableFrame.getContentPane().add(tableScrollPane);

        // Set the size of the table frame
        tableFrame.setSize(600, 400);

        // Set the location of the table frame
        tableFrame.setLocationRelativeTo(null);

        tableFrame.setVisible(true);
    }


    private static double roundToTwoDecimalPlaces(Number value) {
        return Math.round(value.doubleValue() * 100.0) / 100.0;
    }
}
