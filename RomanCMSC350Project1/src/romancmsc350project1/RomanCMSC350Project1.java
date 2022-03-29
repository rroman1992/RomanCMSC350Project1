package romancmsc350project1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class RomanCMSC350Project1 extends JPanel
{
    Border thickBorder = new LineBorder(Color.BLACK, 3);

    private final JLabel messageLabel;
    private final JTextField inputTextField;
    private final JButton prefixToPostfix;
    private final JButton postfixToPrefix;
    private final JLabel answerLabel;
    private final JTextField calcAnswerField;

    public RomanCMSC350Project1()
    {
        setLayout(new BoxLayout (this, BoxLayout.Y_AXIS));

        messageLabel = new JLabel("Enter Expression:");
        messageLabel.setFont(new Font("SansSerif", Font.BOLD, 24));

        inputTextField = new JTextField();
        inputTextField.setBorder(thickBorder);
        inputTextField.setFont(new Font("SansSerif", Font.BOLD, 24));
        inputTextField.setMinimumSize(new Dimension(500, 50));
        inputTextField.setMaximumSize(new Dimension(500, 50));

        prefixToPostfix = new JButton("Prefix to Postfix");
        prefixToPostfix.setBorder(thickBorder);
        prefixToPostfix.setFont(new Font("SansSerif", Font.BOLD, 24));

        postfixToPrefix = new JButton("Postfix to Prefix");
        postfixToPrefix.setBorder(thickBorder);
        postfixToPrefix.setFont(new Font("SansSerif", Font.BOLD, 24));

        answerLabel = new JLabel("Results:");
        answerLabel.setFont(new Font("SansSerif", Font.BOLD, 24));

        calcAnswerField = new JTextField();
        calcAnswerField.setBorder(thickBorder);
        calcAnswerField.setFont(new Font("SansSerif", Font.BOLD, 24));
        calcAnswerField.setMinimumSize(new Dimension (500, 50));
        calcAnswerField.setMaximumSize(new Dimension(500, 50));

        prefixToPostfix.addActionListener(new prefixToPostfixButtonListener(this));
        postfixToPrefix.addActionListener(new postfixToPrefixButtonListener(this));

        Box rowOne = Box.createHorizontalBox();
        rowOne.add(messageLabel);
        Box rowTwo = Box.createHorizontalBox();
        rowTwo.add(inputTextField);
        Box rowThree = Box.createHorizontalBox();
        rowThree.add(prefixToPostfix);
        rowThree.add(Box.createHorizontalStrut(20));
        rowThree.add(postfixToPrefix);
        Box rowFour = Box.createHorizontalBox();
        rowFour.add(answerLabel);
        Box rowFive = Box.createHorizontalBox();
        rowFive.add(calcAnswerField);

        add(rowOne);
        add(rowTwo);
        add(rowThree);
        add(rowFour);
        add(rowFive);
    }

private class prefixToPostfixButtonListener implements ActionListener
{
    RomanCMSC350Project1 project;
    
    static boolean isOperator(char x)
    {
        switch(x) 
        {
        case '+':
        case '-':
        case '/':
        case '*':
            return true;
        }
        return false;
    }
    
    static String prefixToPostfix(String prefix)
    {
        Stack<String> userInput = new Stack<>();
        int length = prefix.length();
        for (int i = length - 1; i >= 0; i--)
        {
            if (isOperator(prefix.charAt(i)))
            {
                String op1 = userInput.peek();
                userInput.pop();
                String op2 = userInput.peek();
                userInput.pop();
                String temp = op1 + op2 + prefix.charAt(i);
                userInput.push(temp);
            }
            else 
            {
                userInput.push(prefix.charAt(i) + "");
            }
        }
        return userInput.peek();
    }
    
    public prefixToPostfixButtonListener(RomanCMSC350Project1 project)
    {
        this.project = project;
    }
    @Override
    public void actionPerformed(ActionEvent e)
        {
            String input;
            String output;
            input = project.inputTextField.getText();                
            output = prefixToPostfix(input);
            project.inputTextField.setText(input);
            project.calcAnswerField.setText((output));
        }
}
    
private class postfixToPrefixButtonListener implements ActionListener
{
    RomanCMSC350Project1 project;
    
    static boolean isOperator(char x)
    {
        switch(x) 
        {
        case '+':
        case '-':
        case '/':
        case '*':
            return true;
        }
        return false;
    }
    
    static String postfixToPrefix(String postfix)
    {
        Stack<String> userInput = new Stack<>();
        int length = postfix.length();
        for (int i = 0; i < length; i++) 
        {
            if (isOperator(postfix.charAt(i))) 
            {
                String op1 = userInput.peek();
                userInput.pop();
                String op2 = userInput.peek();
                userInput.pop();
                String temp = postfix.charAt(i) + op2 + op1;
                userInput.push(temp);
            }
            else 
            {
                userInput.push(postfix.charAt(i) + "");
            }
        }
        String ans = "";
        for (String i : userInput)
            ans += i;
        return ans;
    }
    
    public postfixToPrefixButtonListener(RomanCMSC350Project1 project)
    {
        this.project = project;
    }
    @Override
    public void actionPerformed(ActionEvent e)
        {
            String input;
            String output;
            input = project.inputTextField.getText();                
            output = postfixToPrefix(input);
            project.inputTextField.setText(input);
            project.calcAnswerField.setText((output));
        }
}

    private static void createAndShowGUI() 
    {
        JFrame FinalFrame = new JFrame("Expression Converter");
        FinalFrame.setSize(600, 315);
        FinalFrame.setLocationRelativeTo(null);
        FinalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
        FinalFrame.add(new RomanCMSC350Project1(), BorderLayout.CENTER);
        FinalFrame.setVisible(true);
    }
    public static void main (String[] args)
    {
        SwingUtilities.invokeLater(() -> {
            UIManager.put("swing.boldMetal", Boolean.FALSE);
            createAndShowGUI();
        });
    }
}