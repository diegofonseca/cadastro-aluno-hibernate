/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifro;

import br.edu.ifro.modelo.Aluno;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author 3019657
 */
public class TelaPrincipalController implements Initializable {

    @FXML
    private TableView<?> tbAlunos;
    @FXML
    private TextField n3;
    @FXML
    private TextField n2;
    @FXML
    private TextField n1;
    @FXML
    private ComboBox<?> cb;
        
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("aula");
        EntityManager em = emf.createEntityManager();

        // Busca utilizando HQL
        Query query = em.createQuery("SELECT a FROM Aluno as a");
        List<Aluno> alunos = query.getResultList();

        // Converte lista para observable list
        ObservableList ob = FXCollections.observableArrayList(alunos);
        // Adiciona resultado a Tabela
        tbAlunos.setItems(ob);
        // Adiciona resultado ao Combobox
        cb.setItems(ob);        

        em.close();
        emf.close();
    }      
    
    private void gravar(ActionEvent event) {
        Aluno aluno = new Aluno();
        aluno.setNome("fulano");
        aluno.setCpf("125678");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("aula");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin(); // trasaction
        em.persist(aluno);
        em.getTransaction().commit(); // trasaction
    }

    @FXML
    private void select(MouseEvent event) {        
        Aluno a = (Aluno) tbAlunos.getSelectionModel().getSelectedItem();
        n1.setText(a.getNome());
    }

    @FXML
    private void onPressEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            
        }
    }
    
}
