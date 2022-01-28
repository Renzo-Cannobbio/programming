import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;




//
//  Renzo Cannobbio.
// Student number 23894752
//


 
public class guiCalculator extends JFrame implements ActionListener
 {
     
     
    JTextField display; //calculator's display
    JButton[] buttons;
    JButton[] buttonsSimpleExpressions;
    JButton[] buttonsControl;
    JButton backSpace;
    
    Vector<String> stack= new Vector<String>();
    Vector<Integer> stackNumber = new Vector<Integer>();
    Vector<String> stackOperator= new Vector<String>();
    Boolean isNumber;
    Boolean isOperator;
    
    String[] buttonNames = {"1","2","3","4","5","6","7","8","9","+/-","0","."};
    String[] buttonNamesSimpleExpressions = {"+","-","*","/","!"};
    String[] buttonNamesControl = {"<<","C","(",")","OFF"};
    String[] buttonCommands = {"CMD_1","CMD_2","CMD_3","CMD_4","CMD_5",
        "CMD_6","CMD_7","CMD_8","CMD_9","CMD_+/-","CMD_0","CMD_DOT"};
    String[] buttonCommandsSimpleExpressions = {"CMD_ADD",       // +
                                                "CMD_SUBS",      // -
                                                "CMD_MULTIPLY",  // *
                                                "CMD_DIVISION",  // / 
                                                "CMD_FACTORIAL"  // !
                                               };

    String[] buttonCommandsControl = {"CMD_DELETE_RIGHT",       // <<
                                      "CMD_CLEAR",             // C
                                      "CMD_LEFT_BRACKET",  // (
                                      "CMD_RIGTH_BRACKET",  // )
                                      "OFF"  // OFF 
                                     };
                                               
                                               
                                               
    /**
     * Constructor for objects of class guiCalculator
     */
    public guiCalculator()
    {
        // initialise instance variables
        super("My PROG5001 - Calculator(version)");
        setSize(400, 400);
                 isNumber   = false;
         isOperator = false;

        setMinimumSize(new Dimension(300, 300));
        CreateCalcGUI();
    }

    /**
     * A method to set up the GUI
    */
    private void CreateCalcGUI() {
        
        // define the display
        JPanel panelTop = new JPanel();
        panelTop.setBackground(Color.WHITE);
        GridLayout panelTopLayout = new GridLayout(0,1);
        panelTop.setLayout(panelTopLayout);
        
        // define where the numbers are
        JPanel panelCenter = new JPanel();
        //panelCenter.setBackground(Color.blue);        
        GridLayout panelCenterLayout = new GridLayout(4,3);
        panelCenterLayout.setHgap(10);
        panelCenterLayout.setVgap(10);
        panelCenter.setLayout(panelCenterLayout); 
        
        // simple operators
        JPanel panelSimpleExpressions = new JPanel();
        //panelCenter.setBackground(Color.blue);        
        GridLayout panelSimpleExpressionsLayout = new GridLayout(5,1);
        panelSimpleExpressionsLayout.setHgap(10);
        panelSimpleExpressionsLayout.setVgap(10);
        panelSimpleExpressions.setLayout(panelSimpleExpressionsLayout); 
        
        // big operators
        JPanel panelControl = new JPanel();
        //panelCenter.setBackground(Color.blue);        
        GridLayout panelControlLayout = new GridLayout(5,1);
        panelControlLayout.setHgap(10);
        panelControlLayout.setVgap(10);
        panelControl.setLayout(panelControlLayout); 
        
        // principal panel
        BorderLayout mainLayout = new BorderLayout();
        setLayout(mainLayout);
                
        add(panelTop, BorderLayout.NORTH);
        add(panelCenter, BorderLayout.WEST);
        add(panelSimpleExpressions, BorderLayout.CENTER);
        add(panelControl, BorderLayout.EAST);
        
        //create display
        display = new JTextField("");        
        Font displayFont = new Font("SansSerif", Font.BOLD, 24);
        display.setFont(displayFont);
        display.setHorizontalAlignment(JTextField.RIGHT);        
        display.setPreferredSize(new Dimension(100,35));
        panelTop.add(display);
        
        
        
        //create buttons
        buttons = new JButton[12];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton();
            
            //Color color = new Color(0,255,0); //for example
            buttons[i].setBackground(new Color(209,209,209)); //for example);
            buttons[i].setText(buttonNames[i]);
            buttons[i].setActionCommand(buttonCommands[i]);
            buttons[i].addActionListener(this);
            panelCenter.add(buttons[i]);
        }
        
       //create buttons simple operators
        buttonsSimpleExpressions = new JButton[5];
        for (int i = 0; i < buttonsSimpleExpressions.length; i++) {
            buttonsSimpleExpressions[i] = new JButton();
            buttonsSimpleExpressions[i].setBackground(new Color(209,209,209));
            buttonsSimpleExpressions[i].setText(buttonNamesSimpleExpressions[i]);
            buttonsSimpleExpressions[i].setActionCommand(buttonCommandsSimpleExpressions[i]);
            buttonsSimpleExpressions[i].addActionListener(this);
            panelSimpleExpressions.add(buttonsSimpleExpressions[i]);
        }
        

       //create buttons big operators
        buttonsControl = new JButton[5];
        for (int i = 0; i < buttonsControl.length; i++) 
        {
            buttonsControl[i] = new JButton();
            
            buttonsControl[i].setText(buttonNamesControl[i]);
            if (buttonNamesControl[i] == "OFF")
            {
            buttonsControl[i].setBackground(new Color(255,125,125));
            }
            else
            {
                buttonsControl[i].setBackground(new Color(209,209,209));
            }
            
            
            buttonsControl[i].setActionCommand(buttonCommandsControl[i]);
            buttonsControl[i].addActionListener(this);
            panelControl.add(buttonsControl[i]);
        }        
        
        
        backSpace = new JButton("=");
        backSpace.setBackground(new Color(209,209,209));
        backSpace.addActionListener(this);
        backSpace.setActionCommand("CMD_EQUAL");
        add(backSpace, BorderLayout.SOUTH);
    }
    
    // Add number to the vector of numbers
    private void addNumberToStack(String inputNumber)
    {
            if (isNumber)
            {
                
                int lastIndexVector = stackNumber.size() -1;
                
                int tempNumber = stackNumber.get(lastIndexVector);
                String strTemp = String.valueOf(tempNumber) + inputNumber;  
                tempNumber = Integer.valueOf(strTemp);
                stackNumber.set(lastIndexVector, tempNumber);
            }
            else
            {
                stackNumber.add(Integer.parseInt(inputNumber));
            }
            isNumber = true;
    }
    
    public void actionPerformed(ActionEvent e) {
        String displayText;
        displayText = display.getText();
        int len = displayText.length();
        
        String command = e.getActionCommand(); //define buttons of calculator
        if (command.equals("CMD_1"))
        {
            displayText = displayText + "1";
            
            addNumberToStack("1");       
            
            
        } else
        if (command.equals("CMD_2"))
        {
            displayText = displayText + "2";
            addNumberToStack("2");       
            
        } else
        if (command.equals("CMD_3"))
        {
            displayText = displayText + "3";
            addNumberToStack("3");
        } else
        if (command.equals("CMD_4")) {
            displayText = displayText + "4";
            addNumberToStack("4");       
        } else
        if (command.equals("CMD_5")) {
            displayText = displayText + "5";
            addNumberToStack("5");       
        } else
        if (command.equals("CMD_6")) {
            displayText = displayText + "6";
            addNumberToStack("6");       
        } else
        if (command.equals("CMD_7")) {
            displayText = displayText + "7";
            addNumberToStack("7");       
        } else
        if (command.equals("CMD_8")) {
            displayText = displayText + "8";
            addNumberToStack("8");       
        } else
        if (command.equals("CMD_9")) {
            displayText = displayText + "9";
            addNumberToStack("9");       
        } else
        if (command.equals("CMD_0")) {
            displayText = displayText + "0";
            addNumberToStack("0");       
        } else
        if (command.equals("CMD_DOT")) {
            displayText = displayText + ".";
        } else
        if (command.equals("CMD_C")) {
            displayText = "";
        } 
        else if (command.equals("CMD_DELETE_RIGHT")) 
        {
            displayText = displayText.substring(0, len-1);
        } 
        else if (command.equals("CMD_ADD")) 
        {
           displayText = displayText + "+";
           stackOperator.add("+");
           isNumber = false;           
        }

        else if (command.equals("CMD_SUBS")) 
        {
           displayText = displayText + "-";
           stackOperator.add("-");
           isNumber = false; 
        } 
        else if (command.equals("CMD_MULTIPLY")) 
        {
            displayText = displayText + "*";
            stackOperator.add("*");
           isNumber = false; 
        } 
        else if (command.equals("CMD_DIVISION")) 
        {
            displayText = displayText + "/";
            stackOperator.add("/");
            isNumber = false; 
        } 
        else if (command.equals("CMD_FACTORIAL")) 
        {
            displayText = displayText + "!";
             stackOperator.add("!");
            isNumber = false;             
        } 
        else if (command.equals("CMD_CLEAR")) 
        {
            displayText = "";
            isNumber = false;
            stackNumber.clear();
            stackOperator.clear();
        } 
        else if (command.equals("CMD_LEFT_BRACKET")) 
        {
            displayText = displayText + "(";
        } 
        else if (command.equals("CMD_RIGTH_BRACKET")) 
        {
            displayText = displayText + ")";
        } 
        else if (command.equals("OFF")) 
        {
            this.dispose();
        } 
        else if (command.equals("CMD_EQUAL")) 
        {
            Integer result  = Evaluate();
          
          displayText = result.toString();
            
        } 


        
  
       
        
        
        // change the sign of a number...last number entered
        // example:
        //   4+5  and you press +/- converts to  4+-5   which is 4-5  
        //   4-5    and you press +/- converts to  4--5   which is 4+5
        //   4*5  and you press +/- converts to  4*-5  
        //   4*-5  and you press +/- converts to  4*5
        
        // errors example:
        //   3+4-  
        //   3*4*
        //   4*+5
        else if (command.equals("CMD_+/-")) {
            char number = displayText.charAt(len-1);  
                       
           try
           {
              Integer integerNumber = Integer.parseInt(Character.toString(number));
           } catch (NumberFormatException nfe)
           {
           }
            
            
           } 
                
        
        display.setText(displayText);
    }
    
    
    private int MethodFactorial(int number)
    {
        //n!= 1*2*3...n
        int resultFactorial = 1;
        for(int i=1; i<=number; i++)
        {
            resultFactorial = resultFactorial*i;            
        }
         
        return resultFactorial;
    }    
    
       public Integer Evaluate()
       {
           int v = 0;
           
          while (!stackOperator.isEmpty()) 
          {
            String op= stackOperator.get(stackOperator.size() - 1);
            stackOperator.remove(stackOperator.size() - 1);
            
            v =  stackNumber.get(stackNumber.size() - 1);
            stackNumber.remove(stackNumber.size() - 1);
            
            if      (op.equals("+"))    v = stackNumber.get(stackNumber.size() - 1) + v;
            else if (op.equals("-"))    v = stackNumber.get(stackNumber.size() - 1) - v;
            else if (op.equals("*"))    v = stackNumber.get(stackNumber.size() - 1) * v;
            else if (op.equals("/"))    v = stackNumber.get(stackNumber.size() - 1) / v;
            else if (op.equals("!"))    v = MethodFactorial( v); //8!
            
            if (!stackOperator.isEmpty())
            {
               stackNumber.remove(stackNumber.size() - 1);
            }
            
            stackNumber.add(v);
           }
        
                return v;
       }
    
    
    
    
    public static void main (String[] args)
    {
        guiCalculator calc = new guiCalculator();
        calc.setVisible(true);
    }
}
