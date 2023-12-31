/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package main.ui;

import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import main.Settings;
import main.Tasks;

/**
 *
 * @author Nelson
 */
public class ux extends javax.swing.JFrame {

    public String URLAnimeFLVCap1 = null;
    public int NumCaps = 12;
    public String Server = null;
    public Tasks tasks = null;
    public Settings settings = null;
    public ux(Tasks tasks, Settings settings) {
        initComponents();
        this.tasks = tasks;
        this.settings = settings;
        setIconImage(new ImageIcon(this.settings.iconApp).getImage());
        setTitle(this.settings.appName);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menu_panel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        urlAnimeFLVCap1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        num_cap = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        server = new javax.swing.JComboBox<>();
        btn_init = new javax.swing.JButton();
        job = new javax.swing.JLabel();

        setTitle("Anime FLV Copy Links Donwloader");
        setResizable(false);

        menu_panel.setBackground(new java.awt.Color(255, 255, 255));
        menu_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Anime Mapper Links Downloader");
        menu_panel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, -1, -1));
        menu_panel.add(urlAnimeFLVCap1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 440, -1));

        jLabel2.setText("URL del Capitulo 1");
        menu_panel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        jLabel3.setText("Número de Capitulos");
        menu_panel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));
        menu_panel.add(num_cap, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 120, -1));

        jLabel4.setText("Servidor");
        menu_panel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, -1, -1));

        server.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AnimeFLV" }));
        menu_panel.add(server, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 140, 300, -1));

        btn_init.setText("Iniciar");
        btn_init.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_initActionPerformed(evt);
            }
        });
        menu_panel.add(btn_init, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 80, -1));

        job.setFont(new java.awt.Font("Segoe UI", 3, 10)); // NOI18N
        job.setForeground(new java.awt.Color(204, 0, 0));
        menu_panel.add(job, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, 200, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu_panel, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(510, 280));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_initActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_initActionPerformed
        
        job.setText("Trabajando...");
        URLAnimeFLVCap1 = urlAnimeFLVCap1.getText(); // url
        NumCaps = Integer.parseInt(num_cap.getText());
        Server = server.getSelectedItem().toString();
        btn_init.setEnabled(false);
        
        // Crea un hilo para ejecutar la tarea
        Thread thread = new Thread(() -> {
            tasks.CopyLinksDownloader(URLAnimeFLVCap1, NumCaps, Server);

            // Actualiza la interfaz gráfica
            SwingUtilities.invokeLater(() -> {
                job.setText("");
                btn_init.setEnabled(true);
                
            });
        });

        // Inicia el hilo
        thread.start();
        
    }//GEN-LAST:event_btn_initActionPerformed
    
    /**public void execute(){
        Thread thread = new Thread(() -> {
            // Ejecuta la tarea
            tasks.CopyLinksDownloader(URLAnimeFLVCap1, NumCaps, Server);

            // Actualiza la interfaz gráfica
            job.setText("Tarea completada");
            this.setVisible(false);
        });

        // Inicia el hilo
        thread.start();
    }**/
    
    
    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_init;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel job;
    private javax.swing.JPanel menu_panel;
    private javax.swing.JTextField num_cap;
    private javax.swing.JComboBox<String> server;
    private javax.swing.JTextField urlAnimeFLVCap1;
    // End of variables declaration//GEN-END:variables
}
