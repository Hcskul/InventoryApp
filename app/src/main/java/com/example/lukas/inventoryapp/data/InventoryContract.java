package com.example.lukas.inventoryapp.data;

import android.provider.BaseColumns;

public class InventoryContract {

    public static abstract class InventoryEntry implements BaseColumns {

        private void InventoryContract() {}

        public final static String TABLE_NAME = "inventory";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_PRODUCT_NAME = "name";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_QUANTITY = "quantity";
        public static final String COLUMN_SUPPLIER_NAME = "supplier_name";
        public static final String COLUM_SUPPLIER_NUMBER = "supplier_number";

    }
}
