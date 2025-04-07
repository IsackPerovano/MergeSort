package Controller;

import Model.Model;
import view.View;

import javax.swing.*;

public class Controller{

    private View view;
    private Model model;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
        Configuration();
    }

    private void Configuration(){
        view.getButton1().addActionListener(e -> AddButton());
        view.getButton2().addActionListener(e -> Clean());
        view.getButton3().addActionListener(e -> SortbyName());
        view.getButton4().addActionListener(e -> SortbyDate());
    }

    public void AddButton() {
        try {
            String name = view.getTextField1().getText().trim();
            String dateString = view.getTextField2().getText().trim();

            if(name.isEmpty() || dateString.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Preencher todos os campos", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            model.adicionarDado(name, dateString);

            view.getTextArea1().setText(String.join("\n", model.getDados()));

            view.getTextField1().setText("");
            view.getTextField2().setText("");

        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(view, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erro inesperado: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void Clean(){
        view.getTextArea1().setText("");
    }


    public void SortbyName(){
        String currentText = view.getTextArea1().getText();
        String[] data = currentText.split("\\n");
        String[] sorteData = MergeSort.SortbyName(data);
        view.getTextArea1().setText(String.join("\n",sorteData));
    }

    public void SortbyDate(){
        String currentText = view.getTextArea1().getText();
        String[] data = currentText.split("\\n");
        String[] sorteData = MergeSort.SortbyDate(data);
        view.getTextArea1().setText(String.join("\n",sorteData));
    }



}
