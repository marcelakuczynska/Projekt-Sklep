package interfejsgraficzny;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.KeyedValueComparator;
import org.jfree.data.category.DefaultCategoryDataset;
import sklep.Polka;
import sklep.Regal;
import sklep.Sklep;
import statystyka.StatystykaTygodniowa;


import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Wykresy {
    public static void wykresSprzedazyCalorocznej(Sklep sklep) {
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

        JFreeChart chart = ChartFactory.createBarChart(
                "Przychód ze sprzedaży poszczególnych produktów w ujęciu całorocznym",
                "Nazwa produktu",
                "Wartość",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        wyswietlWykres(chart);
    }

    public static void wykresSprzedazyCalorocznejDlaRegalu(Regal regal) {
        Map<String, Double> dataMap = new HashMap<>(regal.getStatystykaCaloroczna().getStatystykiOgolne());

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (String key : dataMap.keySet()) {
            dataset.addValue(dataMap.get(key), "Przychód ze sprzedaży produktu", key);
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Przychód ze sprzedaży poszczególnych produktów w ujęciu całorocznym na wybranym regale",  // Chart title
                "Nazwa produktu",                 // X-axis label
                "Wartość",                        // Y-axis label
                dataset,                          // Dataset
                PlotOrientation.VERTICAL,         // Plot orientation
                true,                             // Show legend
                true,                             // Use tooltips
                false                             // Configure URLs
        );

        wyswietlWykres(chart);
    }

    public static void wykresSprzedazyTygodniowejDlaRegalu(Regal regal) {

        Map<Polka, StatystykaTygodniowa.WartosciSprzedazy> dataMap = new TreeMap<>(regal.getStatystykaTygodniowa().getWynikSprzedazyProduktu());


        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Polka key : dataMap.keySet()) {
            dataset.addValue(dataMap.get(key).getZysk(), "Przychód ze sprzedaży produktu", key.getTypProduktu()+" - "+key.getProducent());
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Przychód ze sprzedaży poszczególnych produktów w ujęciu tygodniowym",                    // Chart title
                "Nazwa produktu i producent",     // X-axis label
                "Wartość",                        // Y-axis label
                dataset,                          // Dataset
                PlotOrientation.VERTICAL,         // Plot orientation
                true,                             // Show legend
                true,                             // Use tooltips
                false                             // Configure URLs
        );

        wyswietlWykres(chart);
    }

    private static void wyswietlWykres(JFreeChart chart) {
        CategoryPlot plot = chart.getCategoryPlot();
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            frame.add(new ChartPanel(chart), BorderLayout.CENTER);
            frame.setSize(1280, 960);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}

