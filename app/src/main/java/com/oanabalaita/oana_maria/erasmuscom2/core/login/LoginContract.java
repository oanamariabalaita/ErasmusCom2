package com.oanabalaita.oana_maria.erasmuscom2.core.login;

import android.app.Activity;

/**
 * Created by Oana-Maria on 05/06/2017.
 */

public interface LoginContract {

    interface View {
        void onLoginSuccess(String message);

        void onLoginFailure(String message);
    }

    interface Presenter {
        void login(Activity activity, String email, String password);
    }

    interface Interactor {
        void performFirebaseLogin(Activity activity, String email, String password);
    }

    interface OnLoginListener {
        void onSuccess(String message);

        void onFailure(String message);
    }
}
