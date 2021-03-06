package com.example.alertdialogue;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button alert, custom, list, multiple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ALERT DIALOGUE
        alert = findViewById(R.id.AlertDialogue);
        alert.setOnClickListener(v -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

            // Chain together various setter methods to set the dialog characteristics
            builder.setTitle("Login Alert")
                    .setMessage("Are you sure, you want to continue ?")
                    .setCancelable(false)
                    // Add the Yes buttons
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity.this, "Selected Option: YES", Toast.LENGTH_SHORT).show();
                        }
                    })
                    // Add the No buttons
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity.this, "Selected Option: No", Toast.LENGTH_SHORT).show();
                        }
                    });

            //Creating dialog box
            AlertDialog dialog = builder.create();

            //Show dialog on UI
            dialog.show();

        });

        //ALERT DIALOGUE WITH ITEMS LIST
        list = findViewById(R.id.listDialogue);
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder listDiaglogBuilder = new AlertDialog.Builder(MainActivity.this);

                String[] items = getResources().getStringArray(R.array.ArrayColor);

                listDiaglogBuilder.setTitle("Pick a color")
                        .setItems(R.array.ArrayColor, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int position) {
                                String announce = items[position];
                                Toast.makeText(MainActivity.this, announce, Toast.LENGTH_SHORT).show();
                            }
                        });

                //Creating dialog box
                AlertDialog dialog = listDiaglogBuilder.create();

                //Show dialog on UI
                dialog.show();
            }
        });

        //ALERT DIALOGUE WITH MULTIPLE CHOICES
        multiple = findViewById(R.id.multipleDialogue);

        // Where we track the selected items
        ArrayList<Integer> selectedItems = new ArrayList();
        String[] items = getResources().getStringArray(R.array.ArrayColor);
        boolean[] checkedItems = new boolean[items.length];

        /**
         * Ki???m tra c??c gi?? tr??? ch???a trong selectedItems
         * N???u c?? ch???a gi?? tr??? i th?? t???i v??? tr?? i c???a m???ng checkedItems = true
         * c??c v??? tr?? kh??c trong m???ng checkedItems = false
         * */
        for (int i = 0; i < checkedItems.length; i++) {
            if (selectedItems.contains(i)) {
                checkedItems[selectedItems.get(i)] = true;
            } else {
                checkedItems[i] = false;
            }
        }


        multiple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder multipleChoiceDialogBuilder = new AlertDialog.Builder(MainActivity.this);

                multipleChoiceDialogBuilder.setTitle("Multiple")
                        // Specify the list array, the items to be selected by checkedItems array (default is null),
                        // and the listener through which to receive callbacks when items are selected
                        .setMultiChoiceItems(R.array.ArrayColor, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int position, boolean isChecked) {
                                if (isChecked) {
                                    // If the user checked the item, add it's position to the selected items
                                    selectedItems.add(position);
                                } else if (selectedItems.contains(position)) {
                                    // Else, if the item is already in the array, remove it
                                    selectedItems.remove(new Integer(position));
                                    /**
                                     * Trong tr?????ng h???p n??y bu???c ph???i d??ng object Integer v??
                                     * gi?? tr??? position c?? ki???u d??? li???u l?? int n???u kh??ng d??ng object Integer
                                     * vi???t nh?? th??? n??y selectedItems.remove(position);
                                     * ch????ng tr??nh hi???u nh???m position l?? index c???a arraylist v?? s??? d???n ?????n remove nh???m ho???c l???i outofrange arraylist.
                                     * V?? v???y ??? ????y s??? ph???i remove ?????i t?????ng c?? gi?? tr??? integer l?? bi???n position b???ng c??ch vi???t
                                     * selectedItems.remove(new Integer(position));
                                     * */

                                }
                            }
                        })
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                ArrayList<String> itemChoiced = new ArrayList<>();

                                for (int i = 0; i < selectedItems.size(); i++) {
                                    itemChoiced.add(items[selectedItems.get(i)]);
                                }

                                Toast.makeText(MainActivity.this, itemChoiced.toString(), Toast.LENGTH_LONG).show();
                            }
                        });

                //Creating dialog box
                AlertDialog dialog = multipleChoiceDialogBuilder.create();

                //Show dialog on UI
                dialog.show();

            }
        });

        //ALERT DIALOGUE WITH CUSTOM LAYOUT
        custom = findViewById(R.id.CustomDialogue);
        custom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFrag dialogFrag = new DialogFrag();
                dialogFrag.show(getSupportFragmentManager(),"missiles");
            }
        });


    }
}