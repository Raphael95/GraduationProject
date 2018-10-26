package com.example.rapha.transpotsystem;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
<<<<<<< HEAD
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
=======
import android.app.ProgressDialog;
import android.content.ContentValues;
>>>>>>> 9189f46ed660dffc72b2745f5e29f4409ca22d9f
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
<<<<<<< HEAD
import android.graphics.drawable.ColorDrawable;
=======
>>>>>>> 9189f46ed660dffc72b2745f5e29f4409ca22d9f
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
<<<<<<< HEAD
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
=======
import android.view.View;
import android.view.View.OnClickListener;
>>>>>>> 9189f46ed660dffc72b2745f5e29f4409ca22d9f
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
<<<<<<< HEAD
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import adapter.AccountAdapter.OnItemClickListener;
import adapter.AccountAdapter.OnDelBtnClickListener;
=======
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
>>>>>>> 9189f46ed660dffc72b2745f5e29f4409ca22d9f

import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
import adapter.AccountAdapter;
import domain.JiaShiYuan;
=======
>>>>>>> 9189f46ed660dffc72b2745f5e29f4409ca22d9f
import helper.DatabaseHelper;
import httpResolve.ResolveGson;
import httpReuest.RequestOkHttp;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
<<<<<<< HEAD
public class LoginActivity extends AppCompatActivity implements LoaderCallbacks<Cursor>, OnItemClickListener, OnDelBtnClickListener, OnDismissListener {
=======
public class LoginActivity extends AppCompatActivity implements LoaderCallbacks<Cursor> {
>>>>>>> 9189f46ed660dffc72b2745f5e29f4409ca22d9f

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private CheckBox rememberPass;
    private TextView forgetPass;
<<<<<<< HEAD
    private ImageButton arrow;
=======
>>>>>>> 9189f46ed660dffc72b2745f5e29f4409ca22d9f

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    private RequestOkHttp okHttp;
<<<<<<< HEAD
    private LayoutInflater mInflater;
    private PopupWindow mSelectWindow;
    private LinearLayout mInputLayout;
=======
>>>>>>> 9189f46ed660dffc72b2745f5e29f4409ca22d9f
    //private ResolveGson resolveGson=new ResolveGson();

    private static final String TAG="LoginActivity";

<<<<<<< HEAD
    private ArrayList<String> accounts;

=======
>>>>>>> 9189f46ed660dffc72b2745f5e29f4409ca22d9f

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause() called");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart() called");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.d(TAG,"onCreate() called");
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        populateAutoComplete();

        pref= PreferenceManager.getDefaultSharedPreferences(this);

        rememberPass=(CheckBox)findViewById(R.id.remember_pass);
        mPasswordView = (EditText) findViewById(R.id.password);
        boolean isRemember=pref.getBoolean("remember_password",false);
<<<<<<< HEAD
        String account=pref.getString("account","");
        mEmailView.setText(account);
        if(isRemember){
            String password=pref.getString("password","");
=======
        if(isRemember){
            String account=pref.getString("account","");
            String password=pref.getString("password","");
            mEmailView.setText(account);
>>>>>>> 9189f46ed660dffc72b2745f5e29f4409ca22d9f
            mPasswordView.setText(password);
            rememberPass.setChecked(true);
        }
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        Button mSignUpButton =(Button)findViewById(R.id.sign_up_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
        mSignUpButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        forgetPass=(TextView)findViewById(R.id.forget_pass);
        forgetPass.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

<<<<<<< HEAD
        mInputLayout = (LinearLayout) findViewById(R.id.account_form);
        arrow = (ImageButton)findViewById(R.id.arrow);
        initData();
        arrow.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(accounts.size() != 0){
                    arrow.setBackgroundResource(R.drawable.arrow_up);
                    showAccountChoiceWindow();

                }
            }
        });

    }

    private void showAccountChoiceWindow(){
        View view = mInflater.inflate(R.layout.options, null);
        RelativeLayout contentview = (RelativeLayout) view.findViewById(R.id.input_select_listlayout);
        ListView userlist = (ListView) view.findViewById(R.id.account_list);
        userlist.setDividerHeight(0);

        AccountAdapter adapter = new AccountAdapter(this, accounts);
        adapter.setOnItemClickListener(this);
        adapter.setOnDelBtnClickListener(this);
        userlist.setAdapter(adapter);

        mSelectWindow = new PopupWindow(contentview, mInputLayout.getMeasuredWidth(), LayoutParams.WRAP_CONTENT, true);
        mSelectWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mSelectWindow.setOutsideTouchable(true);
        mSelectWindow.setFocusable(true);
        mSelectWindow.setOnDismissListener(this);
        mSelectWindow.showAsDropDown(mInputLayout, 0, 0);
        arrow.setBackgroundResource(R.drawable.arrow_up);

    }

    @Override
    public void onDelBtnClicked(int position)
    {
        mSelectWindow.dismiss();
        final int pos = position;
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setIcon(R.drawable.warning);
        builder.setTitle("警告");
        builder.setMessage("确定删除该账户信息？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String deletedAccount = accounts.remove(pos);
                String inputAccount = mEmailView.getText().toString();
                DataSupport.deleteAll(JiaShiYuan.class, "dianhua= ?", deletedAccount);
                if (inputAccount.equals(deletedAccount))
                {
                    String nextAccount = "";

                    if (accounts.size() != 0)
                        nextAccount = accounts.get(0) + "";

                    mEmailView.setText(nextAccount);
                    mPasswordView.setText("");
                }
                String preAccount = pref.getString("account", "");
                if(preAccount.equals(deletedAccount))
                {
                    pref.edit().clear().commit();
                }

            }
        });
        builder.setNegativeButton("取消", null);
        builder.create().show();

    }

    @Override
    public void onItemClicked(int position)
    {
        mSelectWindow.dismiss();
        mEmailView.setText(accounts.get(position) + "");
        mPasswordView.setText("");
    }

    @Override
    public void onDismiss()
    {
        arrow.setBackgroundResource(R.drawable.select_arrow);
    }



    private void initData(){
        mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        accounts = new ArrayList<>();
        List<JiaShiYuan> jiashiyuans = DataSupport.select("dianhua").find(JiaShiYuan.class);
        for(JiaShiYuan account: jiashiyuans){
            accounts.add(account.getDianhua());
        }
=======
>>>>>>> 9189f46ed660dffc72b2745f5e29f4409ca22d9f

    }

    private void populateAutoComplete() {
        if (!mayRequestContacts()) {
            return;
        }

        getLoaderManager().initLoader(0, null, this);
    }

    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(mEmailView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
                        }
                    });
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

<<<<<<< HEAD

=======
>>>>>>> 9189f46ed660dffc72b2745f5e29f4409ca22d9f
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onStop() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume() called");
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                populateAutoComplete();
            }
        }
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        boolean isChecked=rememberPass.isChecked();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            //showProgress(true);
            mAuthTask = new UserLoginTask(email, password,isChecked);
            mAuthTask.execute((Void) null);
        }
    }

    private boolean isEmailValid(String phone) {
        //TODO: Replace this with your own logic
        return phone.length()==11;
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }


    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mAccount;
        private final String mPassword;
        private final boolean mIsChecked;

        private ProgressDialog dialog;

        UserLoginTask(String email, String password,boolean isChecked) {
            mAccount = email;
            mPassword = password;
            mIsChecked=isChecked;
            dialog=new ProgressDialog(LoginActivity.this);         //创建等待进度条
            dialog.setMessage("正在登录...");
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            okHttp=new RequestOkHttp();

            for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mAccount)) {
                    // Account exists, return true if the password matches.
                    return pieces[1].equals(mPassword);
                }
            }

            if(okHttp.login(mAccount,mPassword)){
                editor=pref.edit();
<<<<<<< HEAD
                editor.putString("account",mAccount);
                if(mIsChecked){
=======
                if(mIsChecked){
                    editor.putString("account",mAccount);
>>>>>>> 9189f46ed660dffc72b2745f5e29f4409ca22d9f
                    editor.putString("password",mPassword);
                    editor.putBoolean("remember_password",true);
                    Log.e("LoginActivity","复选框选中");
                }else{
                    Log.e("LoginActivity","复选框没选中");
                    editor.clear();
                }
                editor.apply();

                if(ResolveGson.checkStatus.equals("未审核")){
                    Intent intent=new Intent(LoginActivity.this,VerifyActivity.class);  //正在审核
                    startActivity(intent);
                    finish();
                }if(ResolveGson.checkStatus.equals("审核通过")){
                    Intent intent=new Intent(LoginActivity.this,MainActivity2.class);  //审核成功
                    startActivity(intent);
                    finish();
                }if(ResolveGson.checkStatus.equals("审核失败")){
                    Intent intent=new Intent(LoginActivity.this,VerifyFailureActivity.class);  //审核未通过
                    startActivity(intent);
                    finish();
                }

            }else{
                return false;
            }

            // TODO: register the new account here.
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);
            dialog.dismiss();

            if (success) {
                finish();
            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }


    }
}

