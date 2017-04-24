
import BD.ConexionMySQL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class Ventana extends javax.swing.JFrame {

    private ConexionMySQL conexion;
    
    
    
    public void rellenarTabla()
    {
        try
        {
            ResultSet consulta= conexion.ejecutarSelect("select * from coche");
            ArrayList<String> nombres= new ArrayList<>();
            
            while(consulta.next())
            {
                nombres.add(consulta.getString("nombre"));
            }
            
            rellenarDatosComboBox(nombres);
            consulta.close();
        }
        catch(SQLException er)
        {
            System.out.println("Error--> "+er.toString());
        }
    }
    public void rellenarDatosComboBox(ArrayList<String> nombres)
    {
        for (int i = 0; i < nombres.size(); i++) 
        {
            cbNombres.addItem(nombres.get(i));
            
        }
    }
    
    public void rellenarTablaMultas(String nombre_conductor)
    {
        try
        {
            DefaultTableModel modelo= (DefaultTableModel) tTabla.getModel();
            modelo.getDataVector().removeAllElements();
            modelo.fireTableDataChanged();

            String consulta="select i.infraccion, m.fecha, i.penalizacion from infraccion i, multa m, "
                    + "coche c where i.codigoInfraccion=m.codigoInfraccion and c.codigo=m.codigoCoche "
                    + "and c.nombre=\""+nombre_conductor+"\"";

            ResultSet resultado= conexion.ejecutarSelect(consulta);
            
            while(resultado.next())
            {
                
                Object[] array={resultado.getString("infraccion"), resultado.getString("fecha"), resultado.getDouble("penalizacion")};
                modelo.addRow(array);
                tTabla.setModel(modelo);
                
            }
            
            resultado.close();
        }
        catch(SQLException er)
        {
            System.out.println("Error rellenando--> "+er.toString());
        }
        
    }
    /**
     * Creates new form Ventana
     */
    public Ventana() {
        initComponents();
        conexion= new ConexionMySQL("root", "", "bdmultas");
        try 
        {
            conexion.conectar();
            rellenarTabla();
        } 
        catch (SQLException ex) 
        {
            System.out.println("Error--> "+ex.toString());
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pNorte = new javax.swing.JPanel();
        lConductor = new javax.swing.JLabel();
        cbNombres = new javax.swing.JComboBox<>();
        pCentro = new javax.swing.JPanel();
        pArriba = new javax.swing.JPanel();
        pCoche = new javax.swing.JPanel();
        lCoche = new javax.swing.JLabel();
        lNombreCoche = new javax.swing.JLabel();
        pMatricula = new javax.swing.JPanel();
        lMatricula = new javax.swing.JLabel();
        lNmatricula = new javax.swing.JLabel();
        pTabla = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tTabla = new javax.swing.JTable();
        pBotones = new javax.swing.JPanel();
        bConsulta = new javax.swing.JButton();
        bSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        pNorte.setLayout(new java.awt.BorderLayout());

        lConductor.setText("Conductor   ");
        pNorte.add(lConductor, java.awt.BorderLayout.LINE_START);

        pNorte.add(cbNombres, java.awt.BorderLayout.CENTER);

        getContentPane().add(pNorte, java.awt.BorderLayout.NORTH);

        pCentro.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos coche", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        pCentro.setLayout(new java.awt.BorderLayout());

        pArriba.setLayout(new java.awt.GridLayout(2, 0));

        pCoche.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lCoche.setText("Coche: ");
        pCoche.add(lCoche);
        pCoche.add(lNombreCoche);

        pArriba.add(pCoche);

        pMatricula.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lMatricula.setText("Matrícula: ");
        pMatricula.add(lMatricula);
        pMatricula.add(lNmatricula);

        pArriba.add(pMatricula);

        pCentro.add(pArriba, java.awt.BorderLayout.NORTH);

        tTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Infracción", "Fecha", "Penalización"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tTabla);

        javax.swing.GroupLayout pTablaLayout = new javax.swing.GroupLayout(pTabla);
        pTabla.setLayout(pTablaLayout);
        pTablaLayout.setHorizontalGroup(
            pTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
        );
        pTablaLayout.setVerticalGroup(
            pTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pTablaLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pCentro.add(pTabla, java.awt.BorderLayout.CENTER);

        bConsulta.setText("Consulta");
        bConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bConsultaActionPerformed(evt);
            }
        });
        pBotones.add(bConsulta);

        bSalir.setText("Salir");
        bSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSalirActionPerformed(evt);
            }
        });
        pBotones.add(bSalir);

        pCentro.add(pBotones, java.awt.BorderLayout.SOUTH);

        getContentPane().add(pCentro, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSalirActionPerformed
        try 
        {
            conexion.desconectar();
            System.exit(0);
        } 
        catch (SQLException ex) 
        {
            System.out.println("Error--> "+ex.toString());
        }
        
    }//GEN-LAST:event_bSalirActionPerformed

    private void bConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bConsultaActionPerformed
        
        
        try
        {
            String consulta="select * from coche where nombre=\""+cbNombres.getSelectedItem().toString()+"\"";
            ResultSet consulta2= conexion.ejecutarSelect(consulta);
            
            if(consulta2.next())
            {
                lNombreCoche.setText(consulta2.getString("Marca")+" - "+ consulta2.getString("Modelo"));
                lNmatricula.setText(consulta2.getString("Matricula"));
            }
            
            consulta2.close();
            rellenarTablaMultas(cbNombres.getSelectedItem().toString());
        }
        catch(SQLException er)
        {
            System.out.println("Error--> "+er.toString());
        }
        
    }//GEN-LAST:event_bConsultaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bConsulta;
    private javax.swing.JButton bSalir;
    private javax.swing.JComboBox<String> cbNombres;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lCoche;
    private javax.swing.JLabel lConductor;
    private javax.swing.JLabel lMatricula;
    private javax.swing.JLabel lNmatricula;
    private javax.swing.JLabel lNombreCoche;
    private javax.swing.JPanel pArriba;
    private javax.swing.JPanel pBotones;
    private javax.swing.JPanel pCentro;
    private javax.swing.JPanel pCoche;
    private javax.swing.JPanel pMatricula;
    private javax.swing.JPanel pNorte;
    private javax.swing.JPanel pTabla;
    private javax.swing.JTable tTabla;
    // End of variables declaration//GEN-END:variables
}