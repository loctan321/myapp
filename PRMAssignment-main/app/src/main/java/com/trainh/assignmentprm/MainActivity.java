package com.trainh.assignmentprm;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.trainh.assignmentprm.adapter.ProductAdapter;
import com.trainh.assignmentprm.database.Database;
import com.trainh.assignmentprm.entities.Account;
import com.trainh.assignmentprm.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Database database;
    EditText edUsername;
    EditText edPassword;
    Button btLogin;
    Button btRegisterPage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = new Database(getApplicationContext());



        edUsername = (EditText) findViewById(R.id.etUsername);
        edPassword = (EditText) findViewById(R.id.etPassword);
        btLogin = (Button) findViewById(R.id.btLogin);
        btRegisterPage = (Button) findViewById(R.id.btRegisterPage);


//        edUsername.setText("ahihi");
//        edPassword.setText("123456");

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = checkLogin();
                if (username != null) {
                    SharedPreferences settings = getApplicationContext().getSharedPreferences("username", 0);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("username", username);
                    editor.apply();
                    Intent intent = new Intent(v.getContext(), HomeActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Không đúng", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btRegisterPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
            }
        });



//        database.createAccount(new Account("loctan", "1"));
        database.createProduct(new Product(R.drawable.bapcaithao, "Bắp cải thảo 500gr", 14000, 50, "Rau", "Bắp cải thảo là loại rau có bẹ lá to, giòn, ngọt thường được dùng để nấu canh, xào chung với rau củ hoặc để muối kim chi. Bắp cải thảo của Bách hóa Xanh được trồng tại Lâm Đồng và đóng gói theo những tiêu chuẩn nghiêm ngặt, bảo đảm các tiêu chuẩn xanh - sạch, chất lượng và an toàn với người dùng."));
        database.createProduct(new Product(R.drawable.caithia, "Cải thìa 4KFarm 500gr", 13000,  50,"Rau", "Cải thìa 4KFarm gói 500g là sản phẩm rau sạch, với ưu điểm 4 KHÔNG được các chị em nội trợ ưa chuộng và tin dùng như không thuốc trừ sâu, không chất tăng trưởng, không chất bảo quản và không biến đổi gen, mang tới giống rau sạch, rau chất lượng"));
        database.createProduct(new Product(R.drawable.caidun, "Cải dún 4KFarm 500g", 14000, 50, "Rau", "Rau an toàn 4KFarm với tiêu chí 4 KHÔNG, luôn ưu tiên bảo vệ sức khỏe người tiêu dùng. Cải bẹ dún 4KFarm chứa nhiều thành phần dinh dưỡng có lợi cho sức khỏe. Có thể nấu canh, xào, cùng với các thực phẩm khác như thịt, cá,... tạo thành nhiều món ăn hấp dẫn cho cả nhà."));
        database.createProduct(new Product(R.drawable.carot, "Cà rốt 500g (2 - 5 củ)", 12000,  50,"Rau", "Cà rốt là một loại củ rất quen thuộc trong các món ăn của người Việt. Cà rốt có hàm lượng chất dinh dưỡng và vitamin A cao, được xem là nguyên liệu cần thiết cho các món ăn dặm của trẻ nhỏ, giúp trẻ sáng mắt và cung cấp nguồn chất xơ dồi dào."));
        database.createProduct(new Product(R.drawable.namkimcham, "Nấm kim châm Hàn Quốc 150g", 10000,  50,"Rau", "Nấm kim châm Hàn Quốc được nuôi trồng và đóng gói theo những tiêu chuẩn nghiêm ngặt, bảo đảm các tiêu chuẩn xanh - sạch, chất lượng và an toàn với người dùng. Sợi nấm dai, giòn và ngọt, khi nấu chín rất thơm nên thường được lăn bột chiên giòn, nấu súp hoặc để nướng ăn kèm."));
        database.createProduct(new Product(R.drawable.thitbongoncuc, "Đùi bò nhập khẩu đông lạnh 500gr", 90000,  50,"Thit", "Thịt đùi có vị ngon tương tự phần mông bò và thường được cắt thành lát dày như bít-tết để nướng. Đùi bò nhập khẩu đông lạnh được cấp đông từ thịt bò tươi ngon là sản phẩm có xuất xứ rõ ràng nên đảm bảo an toàn thực phẩm và giàu chất dinh dưỡng"));
        database.createProduct(new Product(R.drawable.thitbotai, "Thịt bò tái Úc Pacow 250g", 150000, 50, "Thit", "Loại thịt bò với sự kết hợp hoàn hảo giữa mỡ, nạc và gân. Thịt có độ mềm vừa phải, rất ngọt và thơm tự nhiên, thích hợp nhất là dùng để ăn lẩu, phở bò tái, bún bò tái. Thịt bò tái Pacow khay 250g tươi ngon được sản xuất ở Úc bởi Pacow, đã được kiểm duyệt chặc chẽ nên đảm bảo an toàn."));
        database.createProduct(new Product(R.drawable.nambo, "Nạm bò Fohla 250g", 80000,  50,"Thit", "Thịt nạm bò tươi ngon, chất lượng, thịt mềm ngọt. Thịt bò có nguồn gốc xuất xứ rõ ràng, an tâm cho người dùng lựa chọn. Thịt bò là thực phẩm giàu chất dinh dưỡng, chế biến thành nhiều món ăn ngon, hấp dẫn."));
        database.createProduct(new Product(R.drawable.thitbaroi, "Thịt ba rọi bò Thảo Tiến Foods khay 300g", 90000,  50,"Thit", "Thịt bò Mỹ Thảo Tiến Foods là loại thịt bò đông lạnh được thái bằng máy tự động trong môi trường lạnh, tạo nên những khoanh thịt đỏ hồng. Thịt ba rọi bò Mỹ Thảo Tiến Foods khay 300g là phần thịt bò nằm ở phần bụng, có lớp nạc mỡ xen kẽ nhau, tạo nên độ mềm ngọt, thơm ngậy nhưng không ngấy."));




        Cursor dataAccount = database.GetData("SELECT * FROM account");

        while (dataAccount.moveToNext()) {
            Account account = new Account(dataAccount.getString(1), dataAccount.getString(2));
            Log.d("user", dataAccount.getString(0));
            Log.d("password", dataAccount.getString(1));
        }

    }

    private String checkLogin() {
        String username = edUsername.getText().toString();
        String password = edPassword.getText().toString();
        Cursor dataAccount = database.GetData("SELECT * FROM account WHERE username = '" + username + "'" + " AND " + "password = '" + password + "'");
        if (dataAccount.moveToFirst()) {
            SharedPreferences settings = getApplicationContext().getSharedPreferences("idUser", 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("idUser", dataAccount.getString(0));
            editor.apply();
            return username;
        }
        return null;
    }
}