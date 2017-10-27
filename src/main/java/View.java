import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import java.util.Random;
/**
 * @author Sotiris Karapostolakis
 */

public class View extends JFrame{
    private GridBagConstraints constraints;
    private GridBagLayout layout;
    private ButtonGroup balbuttongroup;
    private ButtonGroup modebuttongroup;
    private ButtonGroup memorybuttongroup;
    private JRadioButtonMenuItem[] bal;
    private JRadioButtonMenuItem[] mode1;
    private JRadioButtonMenuItem[] mem;
    private JButton[] buttongame;
    private JButton newgame;
    private JButton confirm;
    private JMenu file;
    private JMenu edit;
    private JMenu balmenu;
    private JMenu modemenu;
    private JMenu memorymenu;
    private JMenuItem aboutitem;
    private JMenuItem instructions;
    private JMenuItem exit;
    private JMenuBar bar;
    private Cards_array v;
    private Icon[] icon;
    private Logic logic;
    private JTextField textfield1;
    private JTextField textfield2;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private Person player1;
    private Person player2;
    private Machine machine;
    private boolean order1=false;
    private int f=-1;
    private int d=0;
    private int length1=0;
    private int f2=-1;
    private int end=0;
    public View()
    {
        super("MemoryGame");
        setLayout(layout=new GridBagLayout());
        constraints=new GridBagConstraints();
        label1=new JLabel("Player 1 Score",SwingConstants.CENTER);
        label2=new JLabel("Player 2 Score",SwingConstants.CENTER);
        label3=new JLabel("Memory Game",SwingConstants.CENTER);
        constraints.fill=GridBagConstraints.HORIZONTAL;
        addComponent(label1,1,0,2,1);
        addComponent(label2,1,2,2,1);
        addComponent(label3,0,1,2,1);
        textfield1=new JTextField(10);
        textfield2=new JTextField(10);
        addComponent(textfield1,2,0,2,1);
        addComponent(textfield2,2,2,2,1);
        logic=new Logic();
        icon=new ImageIcon[11];
        icon[0]=new ImageIcon(getClass().getResource("/0.jpg"));
        icon[1]=new ImageIcon(getClass().getResource("/1.jpg"));
        icon[2]=new ImageIcon(getClass().getResource("/2.jpg"));
        icon[3]=new ImageIcon(getClass().getResource("/3.jpg"));
        icon[4]=new ImageIcon(getClass().getResource("/4.jpg"));
        icon[5]=new ImageIcon(getClass().getResource("/5.jpg"));
        icon[6]=new ImageIcon(getClass().getResource("/6.jpg"));
        icon[7]=new ImageIcon(getClass().getResource("/7.jpg"));
        icon[8]=new ImageIcon(getClass().getResource("/8.jpg"));
        icon[9]=new ImageIcon(getClass().getResource("/9.jpg"));
        icon[10]=new ImageIcon(getClass().getResource("/10.jpg"));
        file=new JMenu("File");
        edit=new JMenu("Edit");
        aboutitem=new JMenuItem("About...");
        instructions=new JMenuItem("Instruction");
        exit=new JMenuItem ("Exit");
        aboutitem.addActionListener(
                event -> JOptionPane.showMessageDialog(View.this,"Creator: Sotiris Karapostolakis\ne-mail: sotkar14@hotmail.com\nYear: 2017","About",JOptionPane.PLAIN_MESSAGE));
        instructions.addActionListener(
                event -> JOptionPane.showMessageDialog(View.this, "1) Define the number of balander cards\n2) Define the kind of play" +
                        "\n3) Define the memory of the computer if it a person-machine game.\n4) Press \"New Game\" button.\nAfter every choice you have to press \"Confirm\" button","Instructions", JOptionPane.PLAIN_MESSAGE));
        exit.addActionListener(
                event -> System.exit(0));
        file.add(aboutitem);
        file.add(instructions);
        file.add(exit);
        bar=new JMenuBar();
        setJMenuBar(bar);
        bar.add(file);
        bar.add(edit);
        ItemHandler itemhandler=new ItemHandler();
        String[] numofbal={"0","1","2","3","4","5","6","7"};
        balmenu=new JMenu("Number of balander cards");
        bal=new JRadioButtonMenuItem[numofbal.length];
        balbuttongroup=new ButtonGroup();
        for(int counter=0;counter<bal.length;counter++)
        {
            bal[counter]=new JRadioButtonMenuItem(numofbal[counter]);
            balmenu.add(bal[counter]);
            balbuttongroup.add(bal[counter]);
        }
        bal[0].setSelected(true);
        edit.add(balmenu);
        edit.addSeparator();
        String[] modes={"Person - Machine","Person - Person"};
        modemenu=new JMenu("Kind of play");
        mode1=new JRadioButtonMenuItem[modes.length];
        modebuttongroup=new ButtonGroup();
        for(int counter=0;counter<modes.length;counter++)
        {
            mode1[counter]=new JRadioButtonMenuItem(modes[counter]);
            modemenu.add(mode1[counter]);
            modebuttongroup.add(mode1[counter]);
        }
        mode1[0].setSelected(true);
        edit.add(modemenu);
        edit.addSeparator();
        String[] numofmem={"0","1","2","3","4","5","6"};
        memorymenu=new JMenu("Machine Memory");
        mem=new JRadioButtonMenuItem[numofmem.length];
        memorybuttongroup=new ButtonGroup();
        for (int counter=0;counter<numofmem.length;counter++)
        {
            mem[counter]=new JRadioButtonMenuItem(numofmem[counter]);
            memorymenu.add(mem[counter]);
            memorybuttongroup.add(mem[counter]);
            mem[counter].addActionListener(itemhandler);
        }
        mem[0].setSelected(true);
        edit.add(memorymenu);
        v=new Cards_array(length1);
        buttongame=new JButton[20];
        constraints.fill=GridBagConstraints.BOTH;
        int row=4;
        int column=0;
        for(int z=0;z<20;z++)
        {
            buttongame[z]=new JButton();
            buttongame[z].setPreferredSize(new Dimension(70,70));
            addComponent(buttongame[z],row,column,1,1);
            if (column==3)
            {
                row++;
                column=0;
            }
            else
                column++;
        }
        confirm=new JButton("Confirm");
        newgame=new JButton("New Game");
        newgame.setPreferredSize(new Dimension(70,70));
        confirm.setPreferredSize(new Dimension(70,70));
        addComponent(newgame,10,0,2,1);
        addComponent(confirm,10,2,2,1);
        ButtonHandler handler=new ButtonHandler();
        newgame.addActionListener(handler);
        confirm.addActionListener(handler);
        for(int counter1=0;counter1<20;counter1++)
        {
            buttongame[counter1].addActionListener(handler);
        }
    }
    private void addComponent(Component component,int row1,int column1,int width1,int height1)
    {
        constraints.gridx=column1;
        constraints.gridy=row1;
        constraints.gridwidth=width1;
        constraints.gridheight=height1;
        layout.setConstraints(component,constraints);
        add(component);
    }
    private class ItemHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent event)
        {
            for(int counter=0;counter<mem.length;counter++)
            {
                if (mem[counter].isSelected())
                {
                    length1=counter;
                }
            }
        }
    }
    private class ButtonHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent event)
        {
            if  (event.getSource()==newgame)
            {
                for(int counter1=0;counter1<20;counter1++)
                {
                    buttongame[counter1].setIcon(null);
                }
                end=0;
                if (mode1[0].isSelected())
                {
                    String name=JOptionPane.showInputDialog("Name of the player");
                    player1=new Person(1,name);
                    label1.setText(name);
                    machine=new Machine(2);
                    label2.setText("Computer");
                    label3.setText("Order : "+name);
                    textfield1.setText("");
                    textfield2.setText("");
                }
                else if(mode1[1].isSelected())
                {
                    String name1=JOptionPane.showInputDialog("Name of the first player");
                    String name2=JOptionPane.showInputDialog("Name of the second player");
                    player1=new Person(1,name1);
                    label1.setText(name1);
                    player2=new Person(2,name2);
                    label2.setText(name2);
                    label3.setText("Order : "+name1);
                    textfield1.setText("");
                    textfield2.setText("");
                }
                for (int counter=0;counter<bal.length;counter++)
                {
                    Random r1=new Random();
                    int[] q={0,0,0,0,0,0,0,0,0,0,0};
                    int y;
                    int t;
                    if(bal[0].isSelected())
                    {
                        for(int k=0;k<20;k++)
                        {
                            y=1+r1.nextInt(10);
                            t=0;
                            while(t!=1)
                            {
                                if (q[y]<2)
                                {
                                    cl_card m=new cl_card(k,y);
                                    v.add_card(m);
                                    q[y]++;
                                    t=1;
                                }
                                else
                                {
                                    y=1+r1.nextInt(10);
                                }
                            }
                        }
                    }
                    else if (bal[1].isSelected())
                    {
                        for(int k=0;k<20;k++)
                        {
                            if ((k==19)&&(q[0]==0))
                            {
                                bal_card m1=new bal_card(19,0);
                                v.add_card(m1);
                                q[0]++;
                            }
                            else
                            {
                                y=r1.nextInt(11);
                                t=0;
                                while(t!=1)
                                {
                                    if ((y==0)&&(q[0]<1))
                                    {
                                        bal_card m1=new bal_card(k,0);
                                        v.add_card(m1);
                                        q[0]++;
                                        t=1;
                                    }
                                    else if ((q[y]<2)&&(y!=0))
                                    {
                                        cl_card m=new cl_card(k,y);
                                        v.add_card(m);
                                        q[y]++;
                                        t=1;
                                    }
                                    else
                                    {
                                        y=r1.nextInt(11);
                                        t=0;
                                    }
                                }
                            }
                        }
                    }
                    else if (bal[2].isSelected())
                    {
                        for(int k=0;k<20;k++)
                        {
                            if (((k==18)||(k==19))&&((q[0]<2)))
                            {
                                bal_card m1=new bal_card(k,0);
                                v.add_card(m1);
                                q[0]++;
                            }
                            else
                            {
                                y=r1.nextInt(11);
                                t=0;
                                while(t!=1)
                                {
                                    if ((y==0)&&(q[0]<2))
                                    {
                                        bal_card m1=new bal_card(k,0);
                                        v.add_card(m1);
                                        q[0]++;
                                        t=1;
                                    }
                                    else if ((q[y]<2)&&(y!=0))
                                    {
                                        cl_card m=new cl_card(k,y);
                                        v.add_card(m);
                                        q[y]++;
                                        t=1;
                                    }
                                    else
                                    {
                                        y=r1.nextInt(11);
                                        t=0;
                                    }
                                }
                            }
                        }
                    }
                    else if (bal[3].isSelected())
                    {
                        for(int k=0;k<20;k++)
                        {
                            if (((k==17)||(k==18)||(k==19))&&(q[0]<3))
                            {
                                bal_card m1=new bal_card(k,0);
                                v.add_card(m1);
                                q[0]++;
                            }
                            else
                            {
                                y=r1.nextInt(11);
                                t=0;
                                while(t!=1)
                                {
                                    if ((y==0)&&(q[0]<3))
                                    {
                                        bal_card m1=new bal_card(k,0);
                                        v.add_card(m1);
                                        q[0]++;
                                        t=1;
                                    }
                                    else if ((q[y]<2)&&(y!=0))
                                    {
                                        cl_card m=new cl_card(k,y);
                                        v.add_card(m);
                                        q[y]++;
                                        t=1;
                                    }
                                    else
                                    {
                                        y=r1.nextInt(11);
                                        t=0;
                                    }
                                }
                            }
                        }
                    }
                    else if (bal[4].isSelected())
                    {
                        for(int k=0;k<20;k++)
                        {
                            if (((k==16)||(k==17)||(k==18)||(k==19))&&(q[0]<4))
                            {
                                bal_card m1=new bal_card(k,0);
                                v.add_card(m1);
                                q[0]++;
                            }
                            else
                            {
                                y=r1.nextInt(11);
                                t=0;
                                while(t!=1)
                                {
                                    if ((y==0)&&(q[0]<4))
                                    {
                                        bal_card m1=new bal_card(k,0);
                                        v.add_card(m1);
                                        q[0]++;
                                        t=1;
                                    }
                                    else if ((q[y]<2)&&(y!=0))
                                    {
                                        cl_card m=new cl_card(k,y);
                                        v.add_card(m);
                                        q[y]++;
                                        t=1;
                                    }
                                    else
                                    {
                                        y=r1.nextInt(11);
                                        t=0;
                                    }
                                }
                            }
                        }
                    }
                    else if (bal[5].isSelected())
                    {
                        for(int k=0;k<20;k++)
                        {
                            if (((k==15)||(k==16)||(k==17)||(k==18)||(k==19))&&(q[0]<5))
                            {
                                bal_card m1=new bal_card(k,0);
                                v.add_card(m1);
                                q[0]++;
                            }
                            else
                            {
                                y=r1.nextInt(11);
                                t=0;
                                while(t!=1)
                                {
                                    if ((y==0)&&(q[0]<5))
                                    {
                                        bal_card m1=new bal_card(k,0);
                                        v.add_card(m1);
                                        q[0]++;
                                        t=1;
                                    }
                                    else if ((q[y]<2)&&(y!=0))
                                    {
                                        cl_card m=new cl_card(k,y);
                                        v.add_card(m);
                                        q[y]++;
                                        t=1;
                                    }
                                    else
                                    {
                                        y=r1.nextInt(11);
                                        t=0;
                                    }
                                }
                            }
                        }
                    }
                    else if (bal[6].isSelected())
                    {
                        for(int k=0;k<20;k++)
                        {
                            if (((k==14)||(k==15)||(k==16)||(k==17)||(k==18)||(k==19))&&(q[0]<6))
                            {
                                bal_card m1=new bal_card(k,0);
                                v.add_card(m1);
                                q[0]++;
                            }
                            else
                            {
                                y=r1.nextInt(11);
                                t=0;
                                while(t!=1)
                                {
                                    if ((y==0)&&(q[0]<6))
                                    {
                                        bal_card m1=new bal_card(k,0);
                                        v.add_card(m1);
                                        q[0]++;
                                        t=1;
                                    }
                                    else if ((q[y]<2)&&(y!=0))
                                    {
                                        cl_card m=new cl_card(k,y);
                                        v.add_card(m);
                                        q[y]++;
                                        t=1;
                                    }
                                    else
                                    {
                                        y=r1.nextInt(11);
                                        t=0;
                                    }
                                }
                            }
                        }
                    }
                    else if (bal[7].isSelected())
                    {
                        for(int k=0;k<20;k++)
                        {
                            if (((k==13)||(k==14)||(k==15)||(k==16)||(k==17)||(k==18)||(k==19))&&(q[0]<3))
                            {
                                bal_card m1=new bal_card(k,0);
                                v.add_card(m1);
                                q[0]++;
                            }
                            else
                            {
                                y=r1.nextInt(11);
                                t=0;
                                while(t!=1)
                                {
                                    if ((y==0)&&(q[0]<7))
                                    {
                                        bal_card m1=new bal_card(k,0);
                                        v.add_card(m1);
                                        q[0]++;
                                        t=1;
                                    }
                                    else if ((q[y]<2)&&(y!=0))
                                    {
                                        cl_card m=new cl_card(k,y);
                                        v.add_card(m);
                                        q[y]++;
                                        t=1;
                                    }
                                    else
                                    {
                                        y=r1.nextInt(11);
                                        t=0;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            else if (event.getSource()==confirm)
            {
                int r;
                if(mode1[0].isSelected())
                {
                    if (order1==false)
                    {
                        if (d==2)
                        {
                            textfield1.setText(""+logic.Run_game1(v.searchcard(f),v.searchcard(f2),player1,machine,order1));
                            logic.flip(buttongame,v,f,f2);
                            order1=!order1;
                            d=0;
                            f=-1;
                            f2=-1;
                            label3.setText(label2.getText());
                            if (logic.endgame(buttongame,v))
                            {
                                JOptionPane.showMessageDialog(View.this, "End of Game", "Completion", JOptionPane.PLAIN_MESSAGE);
                                end=1;
                                order1=false;
                            }
                        }
                        if (order1==true)
                        {
                            r=0;
                            for(int counter3=0;counter3<20;counter3++)
                            {
                                if(buttongame[counter3].getIcon()!=null)
                                    r++;
                            }
                            if (r<19)
                                logic.machinecard(buttongame, v, length1);
                        }
                    }
                    else
                    {
                        if (d==2)
                        {
                            textfield2.setText(""+logic.Run_game1(v.searchcard(f),v.searchcard(f2),player1,machine,order1));
                            if (logic.flipcardmachine(buttongame,v,f,f2)==true)
                            {
                                v.addmemcard(v.searchcard(f));
                                v.addmemcard(v.searchcard(f2));
                            }
                            else
                            {
                                if (v.searchmem(v.searchcard(f).returnid())!=null)
                                    v.deletemem(v.searchcard(f).returnvalue());
                                if (v.searchmem(v.searchcard(f2).returnid())!=null)
                                    v.deletemem(v.searchcard(f2).returnvalue());
                            }
                            order1=!order1;
                            label3.setText(label1.getText());
                            d=0;
                            f=-1;
                            f2=-1;
                            if (logic.endgame(buttongame,v))
                            {
                                JOptionPane.showMessageDialog(View.this, "End of Game", "Completion", JOptionPane.PLAIN_MESSAGE);
                                end=1;
                                order1=false;
                            }
                        }
                    }
                }
                else if(mode1[1].isSelected())
                {
                    if (order1==false)
                    {
                        if (d==2)
                        {
                            textfield1.setText(""+logic.Run_game2(v.searchcard(f),v.searchcard(f2),player1,player2,order1));
                            logic.flip(buttongame,v,f,f2);
                            order1=!order1;
                            d=0;
                            f=-1;
                            f2=-1;
                            label3.setText(label2.getText());
                            if (logic.endgame(buttongame,v))
                            {
                                JOptionPane.showMessageDialog(View.this, "End of Game", "Completion", JOptionPane.PLAIN_MESSAGE);
                                end=1;
                                order1=false;
                            }
                        }
                    }
                    else
                    {
                        if (d==2)
                        {
                            textfield2.setText(""+logic.Run_game2(v.searchcard(f),v.searchcard(f2),player1,player2,order1));
                            logic.flip(buttongame, v, f, f2);
                            order1=!order1;
                            d=0;
                            f=-1;
                            f2=-1;
                            label3.setText(label1.getText());
                            if (logic.endgame(buttongame,v))
                            {
                                JOptionPane.showMessageDialog(View.this, "End of Game", "Completion", JOptionPane.PLAIN_MESSAGE);
                                end=1;
                                order1=false;
                            }
                        }
                    }
                }
            }
            else
            {
                for (int x=0;x<20;x++)
                {
                    int r;
                    if (event.getSource()==buttongame[x])
                    {
                        if ((buttongame[x].getIcon()==null)&&(end!=1))
                        {
                            buttongame[x].setIcon(icon[(v.searchcard(x)).returnvalue()]);
                            d++;
                            if (d==1)
                                f=x;
                            if (d==2)
                                f2=x;
                            if ((d==1)&&(mode1[0].isSelected())&&(order1==true))
                            {
                                f2=x;
                                r=0;
                                for(int counter2=0;counter2<20;counter2++)
                                {
                                    if(buttongame[counter2].getIcon()!=null)
                                        r++;
                                }
                                if (r<20)
                                    logic.machinecard(buttongame, v, length1);
                            }
                        }
                    }
                }
            }
        }
    }
}