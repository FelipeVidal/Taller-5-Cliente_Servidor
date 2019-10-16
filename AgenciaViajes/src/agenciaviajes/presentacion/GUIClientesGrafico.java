/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenciaviajes.presentacion;

import agenciaviajes.negocio.*;
import java.awt.Color;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import mvcf.AModel;
import mvcf.AView;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Felipe
 */
public class GUIClientesGrafico extends JFrame implements AView{
    JPanel panel;
    public GUIClientesGrafico(){
        GestorClientes gestClient = new GestorClientes();
        setTitle("Grafico de Clientes");
        setSize(720,465);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }
 
    private void init(int h, int m) {
        panel = new JPanel();
        getContentPane().add(panel);
        // Fuente de Datos
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(m, "Mujeres", "");
        dataset.setValue(h, "Hombres", "");
        // Creando el Grafico
        JFreeChart chart = ChartFactory.createBarChart3D
        ("","Genero", "Clientes", 
        dataset, PlotOrientation.VERTICAL, true,true, false);
        chart.setBackgroundPaint(Color.cyan);
        chart.getTitle().setPaint(Color.black); 
        CategoryPlot p = chart.getCategoryPlot(); 
        p.setRangeGridlinePaint(Color.red); 
        // Mostrar Grafico
        ChartPanel chartPanel = new ChartPanel(chart);
        panel.add(chartPanel);
        setVisible(true);
    }
    
  

    @Override
    public void actualizar(AModel amodel) {
       
        GestorClientes gestClient = new GestorClientes();
        int h=0;
        try {
            h = gestClient.getTotalHombres();
        } catch (SQLException ex) {
            Logger.getLogger(GUIClientesGrafico.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GUIClientesGrafico.class.getName()).log(Level.SEVERE, null, ex);
        }
        int m=0;
        try {
            m = gestClient.getTotalMujeres();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GUIClientesGrafico.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GUIClientesGrafico.class.getName()).log(Level.SEVERE, null, ex);
        }
        init(h,m);
    }
}
