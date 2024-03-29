/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trab_top_bd;
import java.awt.Container;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;


/**
 *
 * @author Everton
 */
public class TelaQuantUniEsta extends javax.swing.JFrame {
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        Desktop desktop = null;  
        desktop = Desktop.getDesktop();  
        URI uri = null;  
        try {  
                   uri = new URI("http://localhost/projeto_tp_bd/Mapa_cover.php");  
                   desktop.browse(uri);  
        }  
        catch(IOException ioe) {  
                   ioe.printStackTrace();  
        }  
        catch(URISyntaxException use) {  
           use.printStackTrace();  
        }  
   }
   
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
   }
   
    JPanel painelFundo;
    JTable tabela;
    JScrollPane barraRolagem;
    
    String [] colunas = {"Estado", "Municipio", "Universidade",};
    Object [][] dados = {};
    
    DefaultTableModel listTableModel;
    
    public void criaOpcoes(){

    String DB_PATH = "../../Downloads/neo4j-enterprise-2.3.0-M01-windows/neo4j-enterprise-2.3.0-M01/data/graph.db";
    GraphDatabaseService db_Neo = new GraphDatabaseFactory().newEmbeddedDatabase(DB_PATH);
    listTableModel = new DefaultTableModel(dados, colunas);
    
    //lendo a consulta do Neo4J e gravando em um vetor
    try (Transaction ignored = db_Neo.beginTx();
        Result result = db_Neo.execute( "match (e:Estado),(m:Municipio),(i:Instituicao) return i.name, e.name, m.name" ) )
    {
        String rowString1 = null;
        String rowString2 = null;
        String rowString3 = null;
        
        while ( result.hasNext() )
        {
            Map<String,Object> row = result.next();
            int i = 1;
                
            for ( Map.Entry<String,Object> column : row.entrySet() )
            {
                if(i == 3)
                    rowString3 = column.getValue().toString();
                else if(i == 1)
                    rowString1 = column.getValue().toString();
                else if(i == 2)
                    rowString2 = column.getValue().toString();
                
                i++;
            }
            listTableModel.addRow(new Object[]{rowString2, rowString3, rowString1});
        }
    }
    //fechando conexão com o Neo4J      
    db_Neo.shutdown();
    
    setTitle ("Relatorio");
    setSize (1100, 700);
    Container cp = getContentPane();
    
    //Criando JTabbedPane e adicionando ao JFrame  
    JTabbedPane jtp = new JTabbedPane();
    cp.add (jtp);
    
    JPanel painelFundo = new JPanel();
    painelFundo.setLayout (null);
    
    tabela = new JTable(listTableModel);
    JLabel jLabel1 = new JLabel();
    JButton jButton1 = new JButton();
    JButton jButton2 = new JButton();
    
    jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18));
    jLabel1.setText("Abrir Mapa Geográfico");
       
    //setBounds(x, y, width, height)
    jLabel1.setBounds (10, 10, 300, 20);
    painelFundo.add(jLabel1);
    
    jButton1.setText("Abrir navegador");
    jButton1.setBounds (360, 10, 200, 20);
    painelFundo.add(jButton1);
    
    jButton2.setText("Fechar");
    jButton2.setBounds (560, 10, 200, 20);
    painelFundo.add(jButton2);
    
    barraRolagem = new JScrollPane(tabela);
    barraRolagem.setBounds (10, 40, 800, 500);
    painelFundo.add(barraRolagem);
    
    jtp.addTab ("Tela", painelFundo);
    
     jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
     
     jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        
        setVisible(true); 
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 318, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(TelaQuantUniEsta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaQuantUniEsta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaQuantUniEsta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaQuantUniEsta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        TelaQuantUniEsta lc = new TelaQuantUniEsta();
        lc.criaOpcoes();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
