package com.decoder.hamedpa.thetowersofhanoi;
/*
Developed by Hamed pa
De.coder();
https://github.com/hamedpa
 */
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    static EditText n1;
    static EditText n2;
    static EditText md;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        n1= (EditText) findViewById(R.id.max1);

        final TextView an = (TextView) findViewById(R.id.answer);


        Button ans = (Button) findViewById(R.id.aw);
        Button ansR = (Button) findViewById(R.id.rm);


        ans.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Tower tower1=new Tower();
                tower1.nDisks=Integer.parseInt(n1.getText().toString());
                //For hide virtual keyboard
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
                tower1.doTowers(tower1.nDisks,'A','B','C');
                an.setText(tower1.strings.toString());
            }


        });

        ansR.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Tower tower1=new Tower();
                int min = 1;
                int max = 10;
                Random ran = new Random();
                tower1.nDisks=ran.nextInt(max - min + 1) + min;
                tower1.doTowers(tower1.nDisks,'A','B','C');
                an.setText(tower1.strings.toString());
            }


        });






    }
    class Tower{
        int nDisks;
        LinkList strings =new LinkList();
        void doTowers(int topN, char from, char inter, char to) {
            if (topN == 1){
                strings.add("Disk 1 from " + from + " to " + to);
            }else {
                doTowers(topN - 1, from, to, inter);
                strings.add("Disk " + topN + " from " + from + " to " + to);
                doTowers(topN - 1, inter, from, to);
            }
        }
    }

}
class Node{
    Object o;
    Node next;
    Node(){}
    Node(Object o){
        this.o=o;
    }
}
class LinkList{
    Node first;
    LinkList(){
        first=null;
    }
    public void add(Object o){
        Node n=new Node(o);
        Node temp=first;
        if(first!=null){
            while(temp.next!=null){
                temp=temp.next;
            }
            temp.next=n;
        }
        else
            first=n;
    }

    public void removeFirst(){
        Node temp=first;
        if(first!=null){
            first=first.next;
        }
        else
            first=null;
    }

    @Override
    public String toString(){
        String str="";
        Node temp=first;

        while(temp!=null){
            str+=temp.o.toString()+"\n";
            temp=temp.next;
        }
        return str;


    }
}

