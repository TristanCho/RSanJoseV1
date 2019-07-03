//Modificado 00:40:31
package Formularios;

import Logica.VariablesConfig;
import Logica.Cls_Alarma;
import Logica.Cls_Sensores;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;

public class Frm_Alarma extends javax.swing.JFrame {

    private final Cls_Sensores Sensores_Registrados;
    private final Cls_Alarma Actividad;
    private final VariablesConfig DatosConfig;
    private final Timer Temporizador;
    private final List<JRadioButton> ListadoDeSensores;

    public Frm_Alarma() {

        initComponents();

        Sensores_Registrados = new Cls_Sensores();
        Actividad = new Cls_Alarma();
        DatosConfig = new VariablesConfig();
        ListadoDeSensores = new ArrayList<>();

        ListadoDeSensores.add(jlb_Sensor1);
        ListadoDeSensores.add(jlb_Sensor2);
        ListadoDeSensores.add(jlb_Sensor3);
        ListadoDeSensores.add(jlb_Sensor4);
        ListadoDeSensores.add(jlb_Sensor5);
        ListadoDeSensores.add(jlb_Sensor6);
        ListadoDeSensores.add(jlb_Sensor7);
        ListadoDeSensores.add(jlb_Sensor8);
        ListadoDeSensores.add(jlb_Sensor9);
        ListadoDeSensores.add(jlb_Sensor10);
        
      jtf_UserDDBB.setText(DatosConfig.dimeUser());
      jtf_PassDDBB.setText(DatosConfig.dimePass());
      jtf_DDBB.setText(DatosConfig.dimeDDBB());
      jtf_Puerto.setText(DatosConfig.dimePuerto());
      jtf_LocalServer.setText(DatosConfig.dimeLocalServer());
      jtf_RemoteServer.setText(DatosConfig.dimeRemoteServer());
      jtf_RemoteServer2.setText(DatosConfig.dimeRemoteServer2());
      

        int auxInt = 1;
        for (JRadioButton radioButton : ListadoDeSensores) {
            radioButton.setText("Sensor" + auxInt);
            radioButton.setSelected(rootPaneCheckingEnabled);
            auxInt++;
        }

        jcb_armadoNotificaciones.setSelected(true);
        Actividad.Armado_Notificaciones = true;

        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/ALERTA.png")).getImage());
        setLocationRelativeTo(null);

        Temporizador = new Timer();
        TimerTask Tarea = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Temporizador Actualizado");
                try {
                    listar_Actividad();
                    listar_Sensores();
                } catch (InterruptedException | ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(Frm_Alarma.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Fallo en Temporizador");
                }
            }
        };
        Temporizador.schedule(Tarea, 10, 1000);
    }

    private void listar_Actividad() throws InterruptedException, ClassNotFoundException, SQLException {

        jtb_Actividad.setModel(Actividad.Tabla_Actividad());
        jlb_estado.setText(String.valueOf(Actividad.Cadena_Estado));
        jlb_inactividad.setText(String.valueOf(Actividad.Inactividad_Minutos) + " Minutos");
    }

    private void listar_Sensores() throws InterruptedException {
        
        jtb_sensores.setModel(Sensores_Registrados.TomoDatos_Sensores());
        int auxInt = 0;
        for (JRadioButton radioButton : ListadoDeSensores) {
            radioButton.setText(String.valueOf(Sensores_Registrados.SensorNumero.get(auxInt).getNombre()));
            auxInt++;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpanel_Domoyaya = new javax.swing.JPanel();
        TITULO = new javax.swing.JLabel();
        jPanel_Principal = new javax.swing.JPanel();
        Tabulados = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtb_sensores = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtb_Actividad = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jlb_inactividad = new javax.swing.JLabel();
        jlb_estado = new javax.swing.JLabel();
        jlb_Sensor1 = new javax.swing.JRadioButton();
        jlb_Sensor2 = new javax.swing.JRadioButton();
        jlb_Sensor3 = new javax.swing.JRadioButton();
        jlb_Sensor4 = new javax.swing.JRadioButton();
        jlb_Sensor5 = new javax.swing.JRadioButton();
        jlb_Sensor6 = new javax.swing.JRadioButton();
        jlb_Sensor7 = new javax.swing.JRadioButton();
        jlb_Sensor8 = new javax.swing.JRadioButton();
        jlb_Sensor9 = new javax.swing.JRadioButton();
        jlb_Sensor10 = new javax.swing.JRadioButton();
        jcb_armadoNotificaciones = new javax.swing.JCheckBox();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        PANEL_CONFIGURACION = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jtf_RemoteServer = new javax.swing.JTextField();
        jtf_LocalServer = new javax.swing.JTextField();
        jtf_RemoteServer2 = new javax.swing.JTextField();
        jtf_DDBB = new javax.swing.JTextField();
        jtf_Puerto = new javax.swing.JTextField();
        jtf_PassDDBB = new javax.swing.JTextField();
        jtf_UserDDBB = new javax.swing.JTextField();
        btn_Editar = new javax.swing.JButton();
        btn_Guardar = new javax.swing.JButton();
        jbl_InfoConfiguracion = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(486, 670));
        setResizable(false);

        jpanel_Domoyaya.setBackground(new java.awt.Color(102, 204, 255));

        TITULO.setBackground(new java.awt.Color(29, 240, 240));
        TITULO.setFont(new java.awt.Font("Segoe Script", 0, 32)); // NOI18N
        TITULO.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        TITULO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Domoyaya - copia.png"))); // NOI18N
        TITULO.setText("DOMOYAYA 19-07-01");
        TITULO.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        javax.swing.GroupLayout jpanel_DomoyayaLayout = new javax.swing.GroupLayout(jpanel_Domoyaya);
        jpanel_Domoyaya.setLayout(jpanel_DomoyayaLayout);
        jpanel_DomoyayaLayout.setHorizontalGroup(
            jpanel_DomoyayaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TITULO, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
        );
        jpanel_DomoyayaLayout.setVerticalGroup(
            jpanel_DomoyayaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanel_DomoyayaLayout.createSequentialGroup()
                .addComponent(TITULO, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jtb_sensores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtb_sensores.setEnabled(false);
        jtb_sensores.setMaximumSize(new java.awt.Dimension(496, 645));
        jtb_sensores.setMinimumSize(new java.awt.Dimension(496, 645));
        jScrollPane2.setViewportView(jtb_sensores);

        jtb_Actividad.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jtb_Actividad);

        jLabel4.setText("Estado:");

        jLabel5.setText("Inactividad:");

        jlb_inactividad.setBorder(new javax.swing.border.MatteBorder(null));

        jlb_estado.setBorder(new javax.swing.border.MatteBorder(null));

        jlb_Sensor1.setText("Sensor1");
        jlb_Sensor1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jlb_Sensor1ActionPerformed(evt);
            }
        });

        jlb_Sensor2.setText("Sensor2");
        jlb_Sensor2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jlb_Sensor2ActionPerformed(evt);
            }
        });

        jlb_Sensor3.setText("Sensor3");
        jlb_Sensor3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jlb_Sensor3ActionPerformed(evt);
            }
        });

        jlb_Sensor4.setText("Sensor4");
        jlb_Sensor4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jlb_Sensor4ActionPerformed(evt);
            }
        });

        jlb_Sensor5.setText("Sensor5");
        jlb_Sensor5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jlb_Sensor5ActionPerformed(evt);
            }
        });

        jlb_Sensor6.setText("Sensor6");
        jlb_Sensor6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jlb_Sensor6ActionPerformed(evt);
            }
        });

        jlb_Sensor7.setText("Sensor7");
        jlb_Sensor7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jlb_Sensor7ActionPerformed(evt);
            }
        });

        jlb_Sensor8.setText("Sensor8");
        jlb_Sensor8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jlb_Sensor8ActionPerformed(evt);
            }
        });

        jlb_Sensor9.setText("Sensor9");
        jlb_Sensor9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jlb_Sensor9ActionPerformed(evt);
            }
        });

        jlb_Sensor10.setText("Sensor10");
        jlb_Sensor10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jlb_Sensor10ActionPerformed(evt);
            }
        });

        jcb_armadoNotificaciones.setText("NOTIFICACIONES");
        jcb_armadoNotificaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcb_armadoNotificacionesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlb_Sensor5)
                            .addComponent(jlb_Sensor1)
                            .addComponent(jlb_Sensor2)
                            .addComponent(jlb_Sensor3)
                            .addComponent(jlb_Sensor4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jlb_Sensor10)
                            .addComponent(jlb_Sensor9)
                            .addComponent(jlb_Sensor8)
                            .addComponent(jlb_Sensor7)
                            .addComponent(jlb_Sensor6))
                        .addGap(83, 83, 83))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlb_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlb_inactividad, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jcb_armadoNotificaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jcb_armadoNotificaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlb_Sensor1)
                    .addComponent(jlb_Sensor6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlb_Sensor2)
                    .addComponent(jlb_Sensor7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlb_Sensor3)
                    .addComponent(jlb_Sensor8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlb_Sensor4)
                    .addComponent(jlb_Sensor9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlb_Sensor5)
                    .addComponent(jlb_Sensor10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jlb_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jlb_inactividad, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(48, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(116, 116, 116))
        );

        Tabulados.addTab("ACTIVIDAD", jPanel1);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 499, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 647, Short.MAX_VALUE)
        );

        Tabulados.addTab("REGISTROS", jPanel4);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/grafico-radial.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(132, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addContainerGap(327, Short.MAX_VALUE))
        );

        Tabulados.addTab("GRÁFICOS", jPanel5);

        PANEL_CONFIGURACION.setBorder(new javax.swing.border.MatteBorder(null));

        jLabel21.setText("UserDDBB:");

        jLabel2.setText("PassDDBB:");

        jLabel3.setText("Puerto:");

        jLabel22.setText("DDBB:");

        jLabel23.setText("LocalServer:");

        jLabel6.setText("RemoteServer:");

        jLabel7.setText("RemoteServer2:");

        jtf_RemoteServer.setEnabled(false);
        jtf_RemoteServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf_RemoteServerActionPerformed(evt);
            }
        });

        jtf_LocalServer.setEnabled(false);
        jtf_LocalServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf_LocalServerActionPerformed(evt);
            }
        });

        jtf_RemoteServer2.setEnabled(false);
        jtf_RemoteServer2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf_RemoteServer2ActionPerformed(evt);
            }
        });

        jtf_DDBB.setEnabled(false);
        jtf_DDBB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf_DDBBActionPerformed(evt);
            }
        });

        jtf_Puerto.setEnabled(false);
        jtf_Puerto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf_PuertoActionPerformed(evt);
            }
        });

        jtf_PassDDBB.setEnabled(false);
        jtf_PassDDBB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf_PassDDBBActionPerformed(evt);
            }
        });

        jtf_UserDDBB.setEnabled(false);
        jtf_UserDDBB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf_UserDDBBActionPerformed(evt);
            }
        });

        btn_Editar.setText("Editar");
        btn_Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EditarActionPerformed(evt);
            }
        });

        btn_Guardar.setText("Guardar");
        btn_Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_GuardarActionPerformed(evt);
            }
        });

        jbl_InfoConfiguracion.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jbl_InfoConfiguracion.setForeground(new java.awt.Color(0, 0, 255));
        jbl_InfoConfiguracion.setText("Datos Actuales");

        javax.swing.GroupLayout PANEL_CONFIGURACIONLayout = new javax.swing.GroupLayout(PANEL_CONFIGURACION);
        PANEL_CONFIGURACION.setLayout(PANEL_CONFIGURACIONLayout);
        PANEL_CONFIGURACIONLayout.setHorizontalGroup(
            PANEL_CONFIGURACIONLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PANEL_CONFIGURACIONLayout.createSequentialGroup()
                .addGroup(PANEL_CONFIGURACIONLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PANEL_CONFIGURACIONLayout.createSequentialGroup()
                        .addGroup(PANEL_CONFIGURACIONLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(PANEL_CONFIGURACIONLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(PANEL_CONFIGURACIONLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PANEL_CONFIGURACIONLayout.createSequentialGroup()
                                    .addGap(41, 41, 41)
                                    .addGroup(PANEL_CONFIGURACIONLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btn_Editar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(18, 18, 18)
                        .addGroup(PANEL_CONFIGURACIONLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(PANEL_CONFIGURACIONLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jtf_RemoteServer, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jtf_LocalServer, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jtf_RemoteServer2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jtf_DDBB, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jtf_Puerto, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jtf_PassDDBB, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jtf_UserDDBB, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btn_Guardar))
                        .addGap(0, 87, Short.MAX_VALUE))
                    .addGroup(PANEL_CONFIGURACIONLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jbl_InfoConfiguracion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        PANEL_CONFIGURACIONLayout.setVerticalGroup(
            PANEL_CONFIGURACIONLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PANEL_CONFIGURACIONLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jbl_InfoConfiguracion)
                .addGap(18, 18, 18)
                .addGroup(PANEL_CONFIGURACIONLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jtf_UserDDBB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PANEL_CONFIGURACIONLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtf_PassDDBB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PANEL_CONFIGURACIONLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jtf_DDBB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(PANEL_CONFIGURACIONLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jtf_Puerto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PANEL_CONFIGURACIONLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jtf_LocalServer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PANEL_CONFIGURACIONLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jtf_RemoteServer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PANEL_CONFIGURACIONLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jtf_RemoteServer2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(90, 90, 90)
                .addGroup(PANEL_CONFIGURACIONLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Editar)
                    .addComponent(btn_Guardar))
                .addContainerGap(66, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PANEL_CONFIGURACION, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(113, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(PANEL_CONFIGURACION, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 169, Short.MAX_VALUE))
        );

        Tabulados.addTab("CONFIGURACIÓN", jPanel6);

        jPanel9.setBackground(new java.awt.Color(204, 204, 204));
        jPanel9.setBorder(javax.swing.BorderFactory.createCompoundBorder(null, new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true)));

        jLabel20.setFont(new java.awt.Font("Verdana", 2, 14)); // NOI18N
        jLabel20.setText("SENSORES");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(116, Short.MAX_VALUE)
                .addComponent(jLabel20)
                .addGap(112, 112, 112))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel20)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jLabel10.setText("Sensor1");

        jTextField1.setText("Escritorio");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel11.setText("Sensor2");

        jLabel12.setText("Sensor3");

        jLabel13.setText("Sensor4");

        jLabel14.setText("Sensor5");

        jLabel16.setText("Sensor6");

        jLabel17.setText("Sensor7");

        jLabel18.setText("Sensor8");

        jLabel19.setText("Sensor9");

        jLabel15.setText("Sensor10");

        jTextField15.setText("Patio");
        jTextField15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField15ActionPerformed(evt);
            }
        });

        jTextField14.setText("Salón");
        jTextField14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField14ActionPerformed(evt);
            }
        });

        jTextField13.setText("Pasillo");
        jTextField13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField13ActionPerformed(evt);
            }
        });

        jTextField12.setText("BathRoom");
        jTextField12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField12ActionPerformed(evt);
            }
        });

        jTextField11.setText("Puerta");
        jTextField11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField11ActionPerformed(evt);
            }
        });

        jTextField10.setText("Cocina");
        jTextField10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField10ActionPerformed(evt);
            }
        });

        jTextField9.setText("MiddleRoom");
        jTextField9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField9ActionPerformed(evt);
            }
        });

        jTextField8.setText("MainRoom");
        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });

        jTextField7.setText("Puerta Hardware");
        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(101, 101, 101))
                            .addComponent(jLabel10)
                            .addComponent(jLabel15)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19)
                            .addComponent(jLabel14))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextField7)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jTextField8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(180, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15)
                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(247, Short.MAX_VALUE))
        );

        Tabulados.addTab("SENSORES", jPanel2);

        javax.swing.GroupLayout jPanel_PrincipalLayout = new javax.swing.GroupLayout(jPanel_Principal);
        jPanel_Principal.setLayout(jPanel_PrincipalLayout);
        jPanel_PrincipalLayout.setHorizontalGroup(
            jPanel_PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_PrincipalLayout.createSequentialGroup()
                .addComponent(Tabulados, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel_PrincipalLayout.setVerticalGroup(
            jPanel_PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_PrincipalLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Tabulados, javax.swing.GroupLayout.PREFERRED_SIZE, 675, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel_Principal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jpanel_Domoyaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpanel_Domoyaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel_Principal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jcb_armadoNotificacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcb_armadoNotificacionesActionPerformed
        if (jcb_armadoNotificaciones.isSelected()) {
            System.out.println("Notificaciones Armadas");
            Actividad.Armado_Notificaciones = true;

            // Sensores_Registrados.SensorNumero.forEach((sensor) -> {
            //sensor.setActivo(true);
            //});
            ListadoDeSensores.forEach((radioButton) -> {
                radioButton.setSelected(true);
            });

        } else if (jcb_armadoNotificaciones.isSelected() == false) {
            System.out.println("Notificaciones desactivadas....!!!!!!!!!!");
            Actividad.Armado_Notificaciones = false;
        } else {

            System.out.println("La casilla Notificaciones está libre");

            // Sensores_Registrados.SensorNumero.forEach((sensor) -> {
            //     sensor.setActivo(false);
            //  });
            ListadoDeSensores.forEach((radioButton) -> {
                radioButton.setSelected(false);
            });
        }
    }//GEN-LAST:event_jcb_armadoNotificacionesActionPerformed

    private void jlb_Sensor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jlb_Sensor1ActionPerformed
        if (jlb_Sensor1.isSelected()) {
            System.out.println("El sensor 1 está seleccionado");
            Sensores_Registrados.SensorNumero.get(0).setActivo(true);
        } else {
            Sensores_Registrados.SensorNumero.get(0).setActivo(false);
        }
    }//GEN-LAST:event_jlb_Sensor1ActionPerformed

    private void jlb_Sensor2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jlb_Sensor2ActionPerformed
        if (jlb_Sensor2.isSelected()) {
            System.out.println("El sensor 2 está seleccionado");
            Sensores_Registrados.SensorNumero.get(1).setActivo(true);
        } else {
            Sensores_Registrados.SensorNumero.get(1).setActivo(false);
        }
    }//GEN-LAST:event_jlb_Sensor2ActionPerformed

    private void jlb_Sensor3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jlb_Sensor3ActionPerformed
        if (jlb_Sensor3.isSelected()) {
            System.out.println("El sensor 3 está seleccionado");
            Sensores_Registrados.SensorNumero.get(2).setActivo(true);
        } else {
            Sensores_Registrados.SensorNumero.get(2).setActivo(false);
        }
    }//GEN-LAST:event_jlb_Sensor3ActionPerformed

    private void jlb_Sensor4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jlb_Sensor4ActionPerformed
        if (jlb_Sensor4.isSelected()) {
            System.out.println("El sensor 4 está seleccionado");
            Sensores_Registrados.SensorNumero.get(3).setActivo(true);
        } else {
            Sensores_Registrados.SensorNumero.get(3).setActivo(false);
        }
    }//GEN-LAST:event_jlb_Sensor4ActionPerformed

    private void jlb_Sensor5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jlb_Sensor5ActionPerformed
        if (jlb_Sensor5.isSelected()) {
            System.out.println("El sensor 5 está seleccionado");
            Sensores_Registrados.SensorNumero.get(4).setActivo(true);
        } else {
            Sensores_Registrados.SensorNumero.get(4).setActivo(false);
        }
    }//GEN-LAST:event_jlb_Sensor5ActionPerformed

    private void jlb_Sensor6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jlb_Sensor6ActionPerformed
        if (jlb_Sensor6.isSelected()) {
            System.out.println("El sensor 6 está seleccionado");
            Sensores_Registrados.SensorNumero.get(5).setActivo(true);
        } else {
            Sensores_Registrados.SensorNumero.get(5).setActivo(false);
        }
    }//GEN-LAST:event_jlb_Sensor6ActionPerformed

    private void jlb_Sensor7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jlb_Sensor7ActionPerformed
        if (jlb_Sensor7.isSelected()) {
            System.out.println("El sensor 7 está seleccionado");
            Sensores_Registrados.SensorNumero.get(6).setActivo(true);
        } else {
            Sensores_Registrados.SensorNumero.get(6).setActivo(false);
        }
    }//GEN-LAST:event_jlb_Sensor7ActionPerformed

    private void jlb_Sensor8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jlb_Sensor8ActionPerformed
        if (jlb_Sensor8.isSelected()) {
            System.out.println("El sensor 8 está seleccionado");
            Sensores_Registrados.SensorNumero.get(7).setActivo(true);
        } else {
            Sensores_Registrados.SensorNumero.get(7).setActivo(false);
        }
    }//GEN-LAST:event_jlb_Sensor8ActionPerformed

    private void jlb_Sensor9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jlb_Sensor9ActionPerformed
        if (jlb_Sensor9.isSelected()) {
            System.out.println("El sensor 9 está seleccionado");
            Sensores_Registrados.SensorNumero.get(8).setActivo(true);
        } else {
            Sensores_Registrados.SensorNumero.get(8).setActivo(false);
        }
    }//GEN-LAST:event_jlb_Sensor9ActionPerformed

    private void jlb_Sensor10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jlb_Sensor10ActionPerformed
        if (jlb_Sensor10.isSelected()) {
            System.out.println("El sensor 10 está seleccionado");
            Sensores_Registrados.SensorNumero.get(9).setActivo(true);
        } else {
            Sensores_Registrados.SensorNumero.get(9).setActivo(false);
        }
    }//GEN-LAST:event_jlb_Sensor10ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField9ActionPerformed

    private void jTextField10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField10ActionPerformed

    private void jTextField11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField11ActionPerformed

    private void jTextField12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField12ActionPerformed

    private void jTextField13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField13ActionPerformed

    private void jTextField14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField14ActionPerformed

    private void jTextField15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField15ActionPerformed

    private void jtf_RemoteServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf_RemoteServerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtf_RemoteServerActionPerformed

    private void jtf_LocalServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf_LocalServerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtf_LocalServerActionPerformed

    private void jtf_RemoteServer2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf_RemoteServer2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtf_RemoteServer2ActionPerformed

    private void jtf_DDBBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf_DDBBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtf_DDBBActionPerformed

    private void jtf_PuertoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf_PuertoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtf_PuertoActionPerformed

    private void jtf_PassDDBBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf_PassDDBBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtf_PassDDBBActionPerformed

    private void jtf_UserDDBBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf_UserDDBBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtf_UserDDBBActionPerformed

    private void btn_EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EditarActionPerformed
        jbl_InfoConfiguracion.setText("No olvide Guardar los cambios");
        jtf_UserDDBB.setEnabled(true);
        jtf_PassDDBB.setEnabled(true);
        jtf_DDBB.setEnabled(true);
        jtf_Puerto.setEnabled(true);
        jtf_LocalServer.setEnabled(true);
        jtf_RemoteServer.setEnabled(true);
        jtf_RemoteServer2.setEnabled(true);

    }//GEN-LAST:event_btn_EditarActionPerformed

    private void btn_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_GuardarActionPerformed
        jbl_InfoConfiguracion.setText("Reinicie la Aplicación una vez guardados los Cambios");
        jtf_UserDDBB.setEnabled(false);
        jtf_PassDDBB.setEnabled(false);
        jtf_DDBB.setEnabled(false);
        jtf_Puerto.setEnabled(false);
        jtf_LocalServer.setEnabled(false);
        jtf_RemoteServer.setEnabled(false);
        jtf_RemoteServer2.setEnabled(false);

        String UserDDBB=jtf_UserDDBB.getText();
        String PassDDBB=jtf_PassDDBB.getText();
        String DDBB=jtf_DDBB.getText();
        String Puerto=jtf_Puerto.getText();
        String LocalServer=jtf_LocalServer.getText();
        String RemoteServer=jtf_RemoteServer.getText();
        String RemoteServer2=jtf_RemoteServer2.getText();

        DatosConfig.setFormulario(UserDDBB,PassDDBB,DDBB,Puerto,LocalServer,RemoteServer,RemoteServer2);

    }//GEN-LAST:event_btn_GuardarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PANEL_CONFIGURACION;
    private javax.swing.JLabel TITULO;
    private javax.swing.JTabbedPane Tabulados;
    private javax.swing.JButton btn_Editar;
    private javax.swing.JButton btn_Guardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanel_Principal;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JLabel jbl_InfoConfiguracion;
    private javax.swing.JCheckBox jcb_armadoNotificaciones;
    private javax.swing.JRadioButton jlb_Sensor1;
    private javax.swing.JRadioButton jlb_Sensor10;
    private javax.swing.JRadioButton jlb_Sensor2;
    private javax.swing.JRadioButton jlb_Sensor3;
    private javax.swing.JRadioButton jlb_Sensor4;
    private javax.swing.JRadioButton jlb_Sensor5;
    private javax.swing.JRadioButton jlb_Sensor6;
    private javax.swing.JRadioButton jlb_Sensor7;
    private javax.swing.JRadioButton jlb_Sensor8;
    private javax.swing.JRadioButton jlb_Sensor9;
    private javax.swing.JLabel jlb_estado;
    private javax.swing.JLabel jlb_inactividad;
    private javax.swing.JPanel jpanel_Domoyaya;
    private javax.swing.JTable jtb_Actividad;
    private javax.swing.JTable jtb_sensores;
    public javax.swing.JTextField jtf_DDBB;
    public javax.swing.JTextField jtf_LocalServer;
    public javax.swing.JTextField jtf_PassDDBB;
    public javax.swing.JTextField jtf_Puerto;
    public javax.swing.JTextField jtf_RemoteServer;
    public javax.swing.JTextField jtf_RemoteServer2;
    public javax.swing.JTextField jtf_UserDDBB;
    // End of variables declaration//GEN-END:variables
}
