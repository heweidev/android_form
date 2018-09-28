package com.hewei.formblocks;

import android.content.Context;
import android.util.Log;

import com.hewei.formblocks.blocks.SpinnerLine;
import com.hewei.formblocks.blocks.factory.SexBlockFactory;
import com.hewei.formblocks.form.BaseForm;

public class UserForm extends BaseForm {
    private static final String TAG = "UserForm";

    public UserForm(Context context) {
        super(context);
    }

    @Override
    public boolean onEvent(int id) {
        if (id == R.id.action_commit) {
            User user = getData(User.class);
            Log.d(TAG, user.toString());
        }

        return false;
    }

    public static final class User {
        private String name;
        private int age;
        private boolean sex;

        public User() {}

        public User(String name, int age, boolean sex) {
            this.name = name;
            this.age = age;
            this.sex = sex;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public void setSex(boolean sex) {
            this.sex = sex;
        }

        public boolean getSex() {
            return sex;
        }

        @Override
        public String toString() {
            return "name = " + name + ", age = " + age + ", sex = " + sex;
        }
    }
}
