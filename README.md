# Android sqlite

## DataLink
# SQLite Android Implementation with Java

## Overview

This repository contains an Android application demonstrating the use of SQLite for local data storage using Java. The app includes basic CRUD operations (Create, Read, Update, Delete) with an intuitive user interface. Each operation is executed via button clicks, showcasing the use of OnClickListener for user interaction.

## Features

- **SQLite Database Integration**: Efficiently store and manage data locally.
- **CRUD Operations**: Perform Create, Read, Update, and Delete operations on the database.
- **OnClickListener**: Handle button click events for database operations.
- **User Interface**: Simple and user-friendly interface to interact with the database.

## Screenshots

![Main Screen](screenshots/logo.png)
![Add Record](screenshots/logo.png)
![View Records](screenshots/logo.png)

## Installation

1. **Clone the repository**:
   ```sh
   git clone https://github.com/carrington-dev/android_sqlite_data_link.git
   ```
2. **Open in Android Studio**:
    - Open Android Studio.
    - Select `File > Open...`.
    - Navigate to the cloned repository directory and open it.
3. **Build and Run**:
    - Ensure you have an Android device/emulator set up.
    - Click the `Run` button to build and deploy the app.

## Usage

1. **Add Record**:
    - Click on the `Add Record` button.
    - Enter the details and click `Save`.
2. **View Records**:
    - Click on the `View Records` button to see all stored records.
3. **Update Record**:
    - Click on a record from the list to edit.
    - Modify the details and click `Update`.
4. **Delete Record**:
    - Long press on a record from the list to delete.

## Code Structure

### MainActivity.java

Handles the main user interface and initializes button listeners.

### DatabaseHelper.java

Manages the SQLite database creation, version management, and CRUD operations.

### Record.java

Represents the data model for the records stored in the database.

### activity_main.xml

Defines the layout for the main activity, including buttons for each operation.

### record_item.xml

Defines the layout for displaying individual records in a list.

## Example Code

### DatabaseHelper.java

```java
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "mydatabase.db";
    private static final String TABLE_NAME = "mytable";
    private static final String COL_ID = "ID";
    private static final String COL_NAME = "NAME";
    private static final String COL_AGE = "AGE";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, AGE INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Methods for CRUD operations...
}
```

### MainActivity.java

```java
public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    Button btnAddData, btnViewAll;
    EditText editName, editAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        myDb = new DatabaseHelper(this);

        editName = findViewById(R.id.editText_name);
        editAge = findViewById(R.id.editText_age);
        btnAddData = findViewById(R.id.button_add);
        btnViewAll = findViewById(R.id.button_view);

        addData();
        viewAll();
    }

    public void addData() {
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDb.insertData(editName.getText().toString(), editAge.getText().toString());
                if (isInserted)
                    Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void viewAll() {
        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDb.getAllData();
                if (res.getCount() == 0) {
                    showMessage("Error", "Nothing found");
                    return;
                }
                
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("ID :" + res.getString(0) + "\n");
                    buffer.append("Name :" + res.getString(1) + "\n");
                    buffer.append("Age :" + res.getString(2) + "\n\n");
                }
                
                showMessage("Data", buffer.toString());
            }
        });
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
```

## Contributing

Contributions are welcome! Please open an issue or submit a pull request for any changes.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact

For any questions or suggestions, please contact [carrie@stemgon.co.za](mailto:carrie@stemgon.co.za).

---

Feel free to fork and modify this repository as per your needs. Happy coding!