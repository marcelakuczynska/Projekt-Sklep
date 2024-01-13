package interfejsgraficzny;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import sklep.Regal;
import sklep.Sklep;



import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;

public class Wykresy {
    public static void wykresSprzedazy(Sklep sklep) {
        Map<String, Double> dataMap = new HashMap<>();

        for (Regal regal : sklep.getRegalyWSklepie()) {
            dataMap.putAll(regal.getStatystykaCaloroczna().getStatystykiOgolne());
        }

        // Convert HashMap data to DefaultCategoryDataset
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (String key : dataMap.keySet()) {
            dataset.addValue(dataMap.get(key), "Przychód ze sprzedaży poszczególnych produktów", key);
        }

        // Create a chart using JFreeChart
        JFreeChart chart = ChartFactory.createBarChart(
                "Wykres Sprzedaży",                    // Chart title
                "Nazwa produktu",                       // X-axis label
                "Wartość",                          // Y-axis label
                dataset,                          // Dataset
                PlotOrientation.VERTICAL,         // Plot orientation
                true,                             // Show legend
                true,                             // Use tooltips
                false                             // Configure URLs
        );

        // Rotate the category labels
        CategoryPlot plot = chart.getCategoryPlot();
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45); // or your preferred rotation angle

        // Display the chart in a Swing frame
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Bar Chart Example");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Changed from EXIT_ON_CLOSE to DISPOSE_ON_CLOSE
            frame.setLayout(new BorderLayout());
            frame.add(new ChartPanel(chart), BorderLayout.CENTER);

            // Add a window listener to handle window closing
            frame.addWindowListener(new WindowAdapter() {
            });

            frame.setSize(1280, 960);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}

