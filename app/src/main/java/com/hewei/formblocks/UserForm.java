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

    static final class User {
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

        public String getAge() {
            return String.valueOf(age);
        }

        public SpinnerLine.SpinnerItem getSex() {
            return sex ? SexBlockFactory.MALE : SexBlockFactory.FEMALE;
        }

        public void setName(CharSequence name) {
            if (name != null) {
                this.name = name.toString();
            }
        }

        public void setAge(CharSequence age) {
            if (age != null) {
                try {
                    this.age = Integer.parseInt(age.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        public void setSex(SpinnerLine.SpinnerItem item) {
            if (item != null) {
                sex = item == SexBlockFactory.MALE;
            }
        }

        @Override
        public String toString() {
            return "name = " + name + ", age = " + age + ", sex = " + sex;
        }
    }
}
