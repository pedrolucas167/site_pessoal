import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConversorTemperatura extends JFrame {
    private JComboBox<String> fromUnit, toUnit;
    private JTextField inputField, resultField;
    private JButton convertButton;

    public ConversorTemperatura() {
        setTitle("Conversor de Temperaturas");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2));

        String[] unidades = {"Celsius", "Fahrenheit", "Kelvin"};
        fromUnit = new JComboBox<>(unidades);
        toUnit = new JComboBox<>(unidades);
        inputField = new JTextField();
        resultField = new JTextField();
        resultField.setEditable(false);
        convertButton = new JButton("Converter");

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                converter();
            }
        });

        add(new JLabel("De:"));
        add(fromUnit);
        add(new JLabel("Para:"));
        add(toUnit);
        add(new JLabel("Valor:"));
        add(inputField);
        add(convertButton);
        add(resultField);

        setVisible(true);
    }

    private void converter() {
        try {
            double valor = Double.parseDouble(inputField.getText());
            String de = (String) fromUnit.getSelectedItem();
            String para = (String) toUnit.getSelectedItem();
            double resultado = converterTemperatura(valor, de, para);
            resultField.setText(String.format("%.2f", resultado));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private double converterTemperatura(double valor, String de, String para) {
        if (de.equals(para)) return valor;
        
        // Convertendo para Celsius
        double celsius = switch (de) {
            case "Fahrenheit" -> (valor - 32) * 5 / 9;
            case "Kelvin" -> valor - 273.15;
            default -> valor;
        };

        // Convertendo de Celsius para a unidade desejada
        return switch (para) {
            case "Fahrenheit" -> (celsius * 9 / 5) + 32;
            case "Kelvin" -> celsius + 273.15;
            default -> celsius;
        };
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ConversorTemperatura::new);
    }
}
