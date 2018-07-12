package com.example.lukas.inventoryapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lukas.inventoryapp.data.InventoryContract.InventoryEntry;
import com.example.lukas.inventoryapp.data.InventoryDbHelper;

/**
 * Allows user to create a new entry or edit an existing one.
 */
public class EditorActivity extends AppCompatActivity {

    /** EditText field to enter the entry's name */
    private EditText mNameEditText;

    /** EditText field to enter the entry's price */
    private EditText mPriceEditText;

    /** EditText field to enter the entry's quantity */
    private EditText mQuantityEditText;

    /** EditText field to enter the entry's supplier name */
    private EditText mSupplierNameEditText;

    /** EditText field to enter the entry's supplier number */
    private EditText mSupplierNumberEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        // Find all relevant views that we will need to read user input from
        mNameEditText = (EditText) findViewById(R.id.edit_entry_name);
        mPriceEditText = (EditText) findViewById(R.id.edit_price);
        mQuantityEditText = (EditText) findViewById(R.id.edit_quantity);
        mSupplierNameEditText = (EditText) findViewById(R.id.edit_supplier_name);
        mSupplierNumberEditText = (EditText) findViewById(R.id.edit_supplier_number);

    }

    private void insertEntry(){
        // Read from input fields
        // Use trim to eliminate leading or trailing white space
        String nameString = mNameEditText.getText().toString().trim();
        String priceString = mPriceEditText.getText().toString().trim();
        int priceInt = Integer.parseInt(priceString);
        String quantityString = mQuantityEditText.getText().toString().trim();
        int quantityInt = Integer.parseInt(quantityString);
        String supplierNameString = mSupplierNameEditText.getText().toString().trim();
        String supplierNumberString = mSupplierNumberEditText.getText().toString().trim();

        // Create database helper
        InventoryDbHelper mDbHelper = new InventoryDbHelper(this);

        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys
        ContentValues values = new ContentValues();
        values.put(InventoryEntry.COLUMN_PRODUCT_NAME, nameString);
        values.put(InventoryEntry.COLUMN_PRICE, priceInt);
        values.put(InventoryEntry.COLUMN_QUANTITY, quantityInt);
        values.put(InventoryEntry.COLUMN_SUPPLIER_NAME, supplierNameString);
        values.put(InventoryEntry.COLUM_SUPPLIER_NUMBER, supplierNumberString);


        long newRowId = db.insert(InventoryEntry.TABLE_NAME, null, values);

        Log.v("EditorActivity","New row inserted: " + newRowId);

        // Show a toast message depending on whether or not the insertion was successful
        if (newRowId == -1) {
            // If the row ID is -1, then there was an error with insertion.
            Toast.makeText(this, "Error with saving entry", Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID.
            Toast.makeText(this, "Entry saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                // Save entry to the database
                insertEntry();
                // Exit activity
                finish();
                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.action_delete:
                // Do nothing for now
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // Navigate back to parent activity (CatalogActivity)
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
