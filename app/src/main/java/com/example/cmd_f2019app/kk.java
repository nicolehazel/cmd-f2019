package com.example.cmd_f2019app;

import android.os.AsyncTask
import android.widget.TextView;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class query extends AsyncTask{
    @Override
    protected Object doInBackground(Object[] objects) {

        try
            {
                // create our mysql database connection
                //String myDriver = "org.gjt.mm.mysql.Driver";
                String myUrl = "jdbc:mysql://localhost:8080/phpmyadmin?user=hackathon";
                //Class.forName(myDriver);
                Connection conn = DriverManager.getConnection(myUrl);

                // our SQL SELECT query.
                // if you only need a few columns, specify them by name instead of using "*"
                String query = "SELECT option_1,option_2 FROM `db` WHERE UPC = "+ objects[0];
                System.out.println(query);

                // create the java statement
                Statement st = conn.createStatement();

                // execute the query, and get a java resultset
                ResultSet rs = st.executeQuery(query);

                String option_1 = null;

                if(rs.next())
                    option_1 = rs.getString("option_1");


                // iterate through the java resultset
                //while (rs.next())
                //{

                //    String option_1 = rs.getString("option_1");
                //  String option_2 = rs.getString("option_2");

                //String output = option_1.concat(",").concat(option_2);
                //System.out.println(output);


                // print the results

                //}
                st.close();
                return option_1;
            }
            catch (Exception e)
            {
                e.printStackTrace();
                System.err.println("Got an exception! ");
                System.err.println(e.getMessage());
            }

            String warning = "Error";
            return warning;

        }
    public query(){
        private TextView formatTxt, contentTxt
        formatTxt = (TextView) findViewById(R.id.scan_format);
        contentTxt = (TextView) findViewById(R.id.scan_content);
    }

        protected void onPostExecute(String result) {
        formatTxt.setText("FORMAT: " + scanFormat);
        contentTxt.setText("CONTENT: " + result)
    }

}
