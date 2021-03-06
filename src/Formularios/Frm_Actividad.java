//Modificado 00:40:31
package Formularios;

import Conexion.Cls_Conexion;
import Logica.Cls_UltimaActividad;
import Logica.VariablesConfig;
import Logica.Cls_Actividad;
import Logica.Cls_Sensores;
import Logica.Cls_Puerta;
import Logica.Cls_Estadistica;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;

public class Frm_Actividad extends javax.swing.JFrame {

    private final Cls_Sensores Sensores_Registrados;
    private final Cls_Actividad Actividad;
    private final VariablesConfig DatosConfig;
    private final Cls_UltimaActividad UltimaActividad;
    private final Cls_Puerta Puerta;
    private final Cls_Estadistica Barras;
    private final Cls_Estadistica Estadistica;
    private final Timer Temporizador;
    private Boolean tempIsPaused;

    private final List<JRadioButton> ListadoDeSensores;

    private Cls_Conexion cnn;

    public Frm_Actividad() {

        initComponents();

        cnn = new Cls_Conexion();

        Sensores_Registrados = new Cls_Sensores(cnn);
        Actividad = new Cls_Actividad(cnn);
        DatosConfig = new VariablesConfig();
        UltimaActividad = new Cls_UltimaActividad(cnn);
        Puerta = new Cls_Puerta(cnn);
        Barras = new Cls_Estadistica(cnn);
        Estadistica = new Cls_Estadistica(cnn);
        ListadoDeSensores = new ArrayList<>();

        ListadoDeSensores.add(jlb_Sensor1);
        //System.out.println("*****************************************Valor Index 0 = "+ListadoDeSensores.get(0).getText());
        ListadoDeSensores.add(jlb_Sensor2);
        ListadoDeSensores.add(jlb_Sensor3);
        ListadoDeSensores.add(jlb_Sensor4);
        ListadoDeSensores.add(jlb_Sensor5);
        ListadoDeSensores.add(jlb_Sensor6);
        ListadoDeSensores.add(jlb_Sensor7);
        ListadoDeSensores.add(jlb_Sensor8);
        ListadoDeSensores.add(jlb_Sensor9);
        ListadoDeSensores.add(jlb_Sensor10);

        Refresh_Tabulados_Sensores(false);

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
        //System.out.println("*****************************************Valor Index 0 = ");
        jcb_sonido.setSelected(true);
        Actividad.Sonido = true;
        jcb_armadoNotificaciones.setSelected(true);
        Actividad.Armado_Notificaciones = true;
        
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/ALERTA.png")).getImage());
        setLocationRelativeTo(null);

        Temporizador = new Timer();
        tempIsPaused = false;
        TimerTask Tarea = new TimerTask() {
            @Override
            public void run() {
                if (!tempIsPaused) {
                    //System.out.println("Temporizador Actualizado");
                    try {
                        listar_ultimaActividad();
                        listar_Actividad();
                        listar_Sensores();
                        listar_Puerta();
                        listar_Estadistica();
                    } catch (InterruptedException | ClassNotFoundException | SQLException ex) {
                        Logger.getLogger(Frm_Actividad.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println("Fallo en Temporizador");
                    }
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

    private void listar_ultimaActividad() throws InterruptedException, ClassNotFoundException, SQLException {

        jtb_ultimaActividad.setModel(UltimaActividad.Tabla_UltimaActividad());

    }

    private void listar_Puerta() throws InterruptedException, ClassNotFoundException, SQLException {

        jtb_puerta.setModel(Puerta.Tabla_Puerta());

    }
    private void listar_Estadistica() throws InterruptedException, ClassNotFoundException, SQLException {

        jtb_Estadisticas.setModel(Estadistica.Tabla_Estadistica());
        //jlb_ValorPrueba.setText(String.valueOf(Estadistica.ValorPrueba));
       
    }

    private void listar_Sensores() throws InterruptedException {

        jtb_sensores.setModel(Sensores_Registrados.TomoDatos_Sensores());
        int auxInt = 0;
        for (JRadioButton radioButton : ListadoDeSensores) {
            radioButton.setText(String.valueOf(Sensores_Registrados.SensorNumero.get(auxInt).getNombre()));

            auxInt++;
        }
        jtfSensor1.setText(String.valueOf(Sensores_Registrados.SensorNumero.get(0).getNombre()));
        jtfSensor2.setText(String.valueOf(Sensores_Registrados.SensorNumero.get(1).getNombre()));
        jtfSensor3.setText(String.valueOf(Sensores_Registrados.SensorNumero.get(2).getNombre()));
        jtfSensor4.setText(String.valueOf(Sensores_Registrados.SensorNumero.get(3).getNombre()));
        jtfSensor5.setText(String.valueOf(Sensores_Registrados.SensorNumero.get(4).getNombre()));
        jtfSensor6.setText(String.valueOf(Sensores_Registrados.SensorNumero.get(5).getNombre()));
        jtfSensor7.setText(String.valueOf(Sensores_Registrados.SensorNumero.get(6).getNombre()));
        jtfSensor8.setText(String.valueOf(Sensores_Registrados.SensorNumero.get(7).getNombre()));
        jtfSensor9.setText(String.valueOf(Sensores_Registrados.SensorNumero.get(8).getNombre()));
        jtfSensor10.setText(String.valueOf(Sensores_Registrados.SensorNumero.get(9).getNombre()));
    }

    private void Refresh_Tabulados_Sensores(Boolean estado) {
        jtfSensor1.setEnabled(estado);
        jtfSensor2.setEnabled(estado);
        jtfSensor3.setEnabled(estado);
        jtfSensor4.setEnabled(estado);
        jtfSensor5.setEnabled(estado);
        jtfSensor6.setEnabled(estado);
        jtfSensor7.setEnabled(estado);
        jtfSensor8.setEnabled(estado);
        jtfSensor9.setEnabled(estado);
        jtfSensor10.setEnabled(estado);
        //Al modificar,  pausamos el timer principal y bloqueamos el tabcontrol.
        tempIsPaused = estado;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Tabulados = new javax.swing.JTabbedPane();
        PanelActividad = new javax.swing.JPanel();
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
        jcb_sonido = new javax.swing.JCheckBox();
        PanelRegistros = new javax.swing.JPanel();
        BaseRegistro = new javax.swing.JPanel();
        base_ultimaActividad = new javax.swing.JScrollPane();
        jtb_ultimaActividad = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        base_puerta = new javax.swing.JScrollPane();
        jtb_puerta = new javax.swing.JTable();
        PanelEstadisticas = new javax.swing.JPanel();
        base_estadisticas = new javax.swing.JPanel();
        btn_GenerarGrafico = new javax.swing.JButton();
        jlb_ValorPrueba = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtb_Estadisticas = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        baseConfiguracion = new javax.swing.JPanel();
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
        baseListadoSensores = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtb_sensores = new javax.swing.JTable();
        baseConfigSensores = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jtfSensor1 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jtfSensor6 = new javax.swing.JTextField();
        jtfSensor7 = new javax.swing.JTextField();
        jtfSensor8 = new javax.swing.JTextField();
        jtfSensor9 = new javax.swing.JTextField();
        jtfSensor10 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jtfSensor2 = new javax.swing.JTextField();
        jtfSensor3 = new javax.swing.JTextField();
        jtfSensor4 = new javax.swing.JTextField();
        jtfSensor5 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        btnModificar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        panel1 = new java.awt.Panel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(200, 450));
        setResizable(false);

        Tabulados.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                TabuladosStateChanged(evt);
            }
        });

        jtb_Actividad.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jtb_Actividad);

        jLabel4.setText("Estado:");

        jLabel5.setText("Inactividad:");

        jlb_inactividad.setAlignmentX(0.5F);
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

        jcb_sonido.setText("SONIDO");
        jcb_sonido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcb_sonidoActionPerformed(evt);
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
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlb_Sensor5)
                            .addComponent(jlb_Sensor1)
                            .addComponent(jlb_Sensor2)
                            .addComponent(jlb_Sensor3)
                            .addComponent(jlb_Sensor4))
                        .addGap(71, 71, 71)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jlb_Sensor10)
                            .addComponent(jlb_Sensor9)
                            .addComponent(jlb_Sensor8)
                            .addComponent(jlb_Sensor7)
                            .addComponent(jlb_Sensor6))
                        .addGap(123, 123, 123)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlb_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jlb_inactividad, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(40, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcb_armadoNotificaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcb_sonido))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jcb_armadoNotificaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jcb_sonido)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jlb_Sensor1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jlb_Sensor2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jlb_Sensor3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jlb_Sensor4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jlb_Sensor5))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jlb_Sensor6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jlb_Sensor7))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jlb_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jlb_inactividad, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlb_Sensor8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jlb_Sensor9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jlb_Sensor10))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        javax.swing.GroupLayout PanelActividadLayout = new javax.swing.GroupLayout(PanelActividad);
        PanelActividad.setLayout(PanelActividadLayout);
        PanelActividadLayout.setHorizontalGroup(
            PanelActividadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelActividadLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelActividadLayout.setVerticalGroup(
            PanelActividadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelActividadLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(343, 343, 343))
        );

        Tabulados.addTab("ACTIVIDAD", PanelActividad);

        base_ultimaActividad.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jtb_ultimaActividad.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        base_ultimaActividad.setViewportView(jtb_ultimaActividad);

        jLabel1.setText("Ultima Actividad");

        jLabel8.setText("Habitación de Carolina");

        jtb_puerta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        base_puerta.setViewportView(jtb_puerta);

        javax.swing.GroupLayout BaseRegistroLayout = new javax.swing.GroupLayout(BaseRegistro);
        BaseRegistro.setLayout(BaseRegistroLayout);
        BaseRegistroLayout.setHorizontalGroup(
            BaseRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BaseRegistroLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(base_ultimaActividad, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(base_puerta, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(BaseRegistroLayout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addComponent(jLabel1)
                .addGap(210, 210, 210)
                .addComponent(jLabel8)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        BaseRegistroLayout.setVerticalGroup(
            BaseRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BaseRegistroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BaseRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(BaseRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(base_ultimaActividad, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(base_puerta, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PanelRegistrosLayout = new javax.swing.GroupLayout(PanelRegistros);
        PanelRegistros.setLayout(PanelRegistrosLayout);
        PanelRegistrosLayout.setHorizontalGroup(
            PanelRegistrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelRegistrosLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(BaseRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );
        PanelRegistrosLayout.setVerticalGroup(
            PanelRegistrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelRegistrosLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(BaseRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        Tabulados.addTab("REGISTROS", PanelRegistros);

        PanelEstadisticas.setInheritsPopupMenu(true);
        PanelEstadisticas.setMaximumSize(new java.awt.Dimension(300, 32767));

        btn_GenerarGrafico.setBackground(new java.awt.Color(204, 204, 204));
        btn_GenerarGrafico.setForeground(new java.awt.Color(51, 51, 255));
        btn_GenerarGrafico.setText("Generar Gráfico");
        btn_GenerarGrafico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_GenerarGraficoActionPerformed(evt);
            }
        });

        jlb_ValorPrueba.setText("Valor de Prueba");

        javax.swing.GroupLayout base_estadisticasLayout = new javax.swing.GroupLayout(base_estadisticas);
        base_estadisticas.setLayout(base_estadisticasLayout);
        base_estadisticasLayout.setHorizontalGroup(
            base_estadisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(base_estadisticasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(base_estadisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jlb_ValorPrueba, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_GenerarGrafico))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        base_estadisticasLayout.setVerticalGroup(
            base_estadisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, base_estadisticasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_GenerarGrafico)
                .addGap(50, 50, 50)
                .addComponent(jlb_ValorPrueba)
                .addContainerGap(136, Short.MAX_VALUE))
        );

        jtb_Estadisticas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(jtb_Estadisticas);

        jLabel9.setText("Día de Hoy");

        javax.swing.GroupLayout PanelEstadisticasLayout = new javax.swing.GroupLayout(PanelEstadisticas);
        PanelEstadisticas.setLayout(PanelEstadisticasLayout);
        PanelEstadisticasLayout.setHorizontalGroup(
            PanelEstadisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelEstadisticasLayout.createSequentialGroup()
                .addGroup(PanelEstadisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelEstadisticasLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelEstadisticasLayout.createSequentialGroup()
                        .addGap(187, 187, 187)
                        .addComponent(jLabel9)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(base_estadisticas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(565, 565, 565))
        );
        PanelEstadisticasLayout.setVerticalGroup(
            PanelEstadisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelEstadisticasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelEstadisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelEstadisticasLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(base_estadisticas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        Tabulados.addTab("ESTADISTICAS", PanelEstadisticas);

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
                        .addGap(41, 41, 41)
                        .addComponent(jbl_InfoConfiguracion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(PANEL_CONFIGURACIONLayout.createSequentialGroup()
                        .addGroup(PANEL_CONFIGURACIONLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(PANEL_CONFIGURACIONLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(PANEL_CONFIGURACIONLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PANEL_CONFIGURACIONLayout.createSequentialGroup()
                                    .addGap(41, 41, 41)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(PANEL_CONFIGURACIONLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtf_DDBB, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtf_Puerto, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtf_PassDDBB, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtf_UserDDBB, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(69, 69, 69)
                        .addGroup(PANEL_CONFIGURACIONLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(PANEL_CONFIGURACIONLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtf_RemoteServer, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtf_LocalServer, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtf_RemoteServer2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(PANEL_CONFIGURACIONLayout.createSequentialGroup()
                .addGap(217, 217, 217)
                .addComponent(btn_Editar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_Guardar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PANEL_CONFIGURACIONLayout.setVerticalGroup(
            PANEL_CONFIGURACIONLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PANEL_CONFIGURACIONLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbl_InfoConfiguracion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PANEL_CONFIGURACIONLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PANEL_CONFIGURACIONLayout.createSequentialGroup()
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
                            .addComponent(jtf_Puerto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(PANEL_CONFIGURACIONLayout.createSequentialGroup()
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
                            .addComponent(jtf_RemoteServer2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(PANEL_CONFIGURACIONLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Editar)
                    .addComponent(btn_Guardar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout baseConfiguracionLayout = new javax.swing.GroupLayout(baseConfiguracion);
        baseConfiguracion.setLayout(baseConfiguracionLayout);
        baseConfiguracionLayout.setHorizontalGroup(
            baseConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(baseConfiguracionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PANEL_CONFIGURACION, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(95, Short.MAX_VALUE))
        );
        baseConfiguracionLayout.setVerticalGroup(
            baseConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(baseConfiguracionLayout.createSequentialGroup()
                .addComponent(PANEL_CONFIGURACION, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        Tabulados.addTab("CONFIGURACIÓN", baseConfiguracion);

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

        javax.swing.GroupLayout baseListadoSensoresLayout = new javax.swing.GroupLayout(baseListadoSensores);
        baseListadoSensores.setLayout(baseListadoSensoresLayout);
        baseListadoSensoresLayout.setHorizontalGroup(
            baseListadoSensoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(baseListadoSensoresLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(240, Short.MAX_VALUE))
        );
        baseListadoSensoresLayout.setVerticalGroup(
            baseListadoSensoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(baseListadoSensoresLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Tabulados.addTab("LISTADO SENSORES", baseListadoSensores);

        jLabel10.setText("Sensor1");

        jtfSensor1.setText("S1");
        jtfSensor1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfSensor1ActionPerformed(evt);
            }
        });

        jLabel16.setText("Sensor6");

        jtfSensor6.setText("S6");
        jtfSensor6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfSensor6ActionPerformed(evt);
            }
        });

        jtfSensor7.setText("S7");
        jtfSensor7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfSensor7ActionPerformed(evt);
            }
        });

        jtfSensor8.setText("S8");
        jtfSensor8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfSensor8ActionPerformed(evt);
            }
        });

        jtfSensor9.setText("S9");
        jtfSensor9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfSensor9ActionPerformed(evt);
            }
        });

        jtfSensor10.setText("S10");
        jtfSensor10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfSensor10ActionPerformed(evt);
            }
        });

        jLabel15.setText("Sensor10");

        jLabel19.setText("Sensor9");

        jLabel18.setText("Sensor8");

        jLabel17.setText("Sensor7");

        jtfSensor2.setText("S2");
        jtfSensor2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfSensor2ActionPerformed(evt);
            }
        });

        jtfSensor3.setText("S3");
        jtfSensor3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfSensor3ActionPerformed(evt);
            }
        });

        jtfSensor4.setText("S4");
        jtfSensor4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfSensor4ActionPerformed(evt);
            }
        });

        jtfSensor5.setText("S5");
        jtfSensor5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfSensor5ActionPerformed(evt);
            }
        });

        jLabel14.setText("Sensor5");

        jLabel13.setText("Sensor4");

        jLabel12.setText("Sensor3");

        jLabel11.setText("Sensor2");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jtfSensor2, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jtfSensor1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jtfSensor3, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jtfSensor4, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jtfSensor5, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jtfSensor7, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jtfSensor8, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jtfSensor9, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jtfSensor10, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jtfSensor6, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap()))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jtfSensor1, jtfSensor10, jtfSensor2, jtfSensor3, jtfSensor4, jtfSensor5, jtfSensor6, jtfSensor7, jtfSensor8, jtfSensor9});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel10, jLabel11, jLabel12, jLabel13, jLabel14});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel15, jLabel16, jLabel17, jLabel18, jLabel19});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 166, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel10)
                                .addComponent(jtfSensor1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jtfSensor6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel11)
                                .addComponent(jtfSensor2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel12)
                                .addComponent(jtfSensor3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel13)
                                .addComponent(jtfSensor4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel14)
                                .addComponent(jtfSensor5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(26, 26, 26)
                            .addComponent(jLabel17)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel18)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel19)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel15))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(32, 32, 32)
                            .addComponent(jtfSensor7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jtfSensor8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jtfSensor9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(9, 9, 9)
                            .addComponent(jtfSensor10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel10, jLabel11, jLabel12, jLabel13, jLabel14, jLabel15, jLabel16, jLabel17, jLabel18, jLabel19, jtfSensor1, jtfSensor10, jtfSensor2, jtfSensor3, jtfSensor4, jtfSensor5, jtfSensor6, jtfSensor7, jtfSensor8, jtfSensor9});

        jPanel9.setBackground(new java.awt.Color(204, 204, 204));
        jPanel9.setBorder(javax.swing.BorderFactory.createCompoundBorder(null, new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true)));

        jLabel20.setFont(new java.awt.Font("Verdana", 2, 14)); // NOI18N
        jLabel20.setText("SENSORES");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(270, 270, 270)
                .addComponent(jLabel20)
                .addContainerGap(274, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel20)
                .addContainerGap())
        );

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnGuardar.setText("Aceptar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout baseConfigSensoresLayout = new javax.swing.GroupLayout(baseConfigSensores);
        baseConfigSensores.setLayout(baseConfigSensoresLayout);
        baseConfigSensoresLayout.setHorizontalGroup(
            baseConfigSensoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(baseConfigSensoresLayout.createSequentialGroup()
                .addGroup(baseConfigSensoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(baseConfigSensoresLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(baseConfigSensoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(baseConfigSensoresLayout.createSequentialGroup()
                        .addGap(224, 224, 224)
                        .addComponent(btnModificar)
                        .addGap(28, 28, 28)
                        .addComponent(btnGuardar)))
                .addContainerGap(67, Short.MAX_VALUE))
        );
        baseConfigSensoresLayout.setVerticalGroup(
            baseConfigSensoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(baseConfigSensoresLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(baseConfigSensoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificar)
                    .addComponent(btnGuardar))
                .addContainerGap())
        );

        Tabulados.addTab("SENSORES", baseConfigSensores);

        panel1.setBackground(new java.awt.Color(51, 204, 255));

        jTextField1.setBackground(new java.awt.Color(51, 204, 255));
        jTextField1.setFont(new java.awt.Font("MV Boli", 2, 36)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("Domoyaya 21-01-24");
        jTextField1.setToolTipText("");
        jTextField1.setBorder(null);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(51, 204, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Escalada.PNG"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/1 - copia.PNG"))); // NOI18N

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(37, 37, 37)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton1)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Tabulados, javax.swing.GroupLayout.PREFERRED_SIZE, 704, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(Tabulados, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void jtfSensor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfSensor1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfSensor1ActionPerformed

    private void jtfSensor2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfSensor2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfSensor2ActionPerformed

    private void jtfSensor3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfSensor3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfSensor3ActionPerformed

    private void jtfSensor4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfSensor4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfSensor4ActionPerformed

    private void jtfSensor5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfSensor5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfSensor5ActionPerformed

    private void jtfSensor6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfSensor6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfSensor6ActionPerformed

    private void jtfSensor7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfSensor7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfSensor7ActionPerformed

    private void jtfSensor8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfSensor8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfSensor8ActionPerformed

    private void jtfSensor9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfSensor9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfSensor9ActionPerformed

    private void jtfSensor10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfSensor10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfSensor10ActionPerformed

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
        Refresh_Tabulados_Sensores(true);

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

        String UserDDBB = jtf_UserDDBB.getText();
        String PassDDBB = jtf_PassDDBB.getText();
        String DDBB = jtf_DDBB.getText();
        String Puerto = jtf_Puerto.getText();
        String LocalServer = jtf_LocalServer.getText();
        String RemoteServer = jtf_RemoteServer.getText();
        String RemoteServer2 = jtf_RemoteServer2.getText();

        DatosConfig.setFormulario(UserDDBB, PassDDBB, DDBB, Puerto, LocalServer, RemoteServer, RemoteServer2);
        Refresh_Tabulados_Sensores(false);
        System.exit(0);
    }//GEN-LAST:event_btn_GuardarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        Refresh_Tabulados_Sensores(true);
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        Refresh_Tabulados_Sensores(false);
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void TabuladosStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_TabuladosStateChanged
        Refresh_Tabulados_Sensores(false);
    }//GEN-LAST:event_TabuladosStateChanged

    private void btn_GenerarGraficoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_GenerarGraficoActionPerformed
      Barras.generarBarras();
    }//GEN-LAST:event_btn_GenerarGraficoActionPerformed

    private void jcb_sonidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcb_sonidoActionPerformed
       if(jcb_sonido.isSelected()){
           
           jcb_sonido.setSelected(true);
           Actividad.Sonido = true;
       }else if(jcb_sonido.isSelected()== false){
           System.out.println(" jcb desactivado");
           Actividad.Sonido = false;
       }else{
           System.out.println(" Casilla Sonido sin identificar");
       }
    }//GEN-LAST:event_jcb_sonidoActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BaseRegistro;
    private javax.swing.JPanel PANEL_CONFIGURACION;
    private javax.swing.JPanel PanelActividad;
    private javax.swing.JPanel PanelEstadisticas;
    private javax.swing.JPanel PanelRegistros;
    private javax.swing.JTabbedPane Tabulados;
    private javax.swing.JPanel baseConfigSensores;
    private javax.swing.JPanel baseConfiguracion;
    private javax.swing.JPanel baseListadoSensores;
    private javax.swing.JPanel base_estadisticas;
    private javax.swing.JScrollPane base_puerta;
    private javax.swing.JScrollPane base_ultimaActividad;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btn_Editar;
    private javax.swing.JButton btn_GenerarGrafico;
    private javax.swing.JButton btn_Guardar;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel jbl_InfoConfiguracion;
    private javax.swing.JCheckBox jcb_armadoNotificaciones;
    private javax.swing.JCheckBox jcb_sonido;
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
    private javax.swing.JLabel jlb_ValorPrueba;
    private javax.swing.JLabel jlb_estado;
    private javax.swing.JLabel jlb_inactividad;
    private javax.swing.JTable jtb_Actividad;
    private javax.swing.JTable jtb_Estadisticas;
    private javax.swing.JTable jtb_puerta;
    private javax.swing.JTable jtb_sensores;
    private javax.swing.JTable jtb_ultimaActividad;
    private javax.swing.JTextField jtfSensor1;
    private javax.swing.JTextField jtfSensor10;
    private javax.swing.JTextField jtfSensor2;
    private javax.swing.JTextField jtfSensor3;
    private javax.swing.JTextField jtfSensor4;
    private javax.swing.JTextField jtfSensor5;
    private javax.swing.JTextField jtfSensor6;
    private javax.swing.JTextField jtfSensor7;
    private javax.swing.JTextField jtfSensor8;
    private javax.swing.JTextField jtfSensor9;
    public javax.swing.JTextField jtf_DDBB;
    public javax.swing.JTextField jtf_LocalServer;
    public javax.swing.JTextField jtf_PassDDBB;
    public javax.swing.JTextField jtf_Puerto;
    public javax.swing.JTextField jtf_RemoteServer;
    public javax.swing.JTextField jtf_RemoteServer2;
    public javax.swing.JTextField jtf_UserDDBB;
    private java.awt.Panel panel1;
    // End of variables declaration//GEN-END:variables
}
