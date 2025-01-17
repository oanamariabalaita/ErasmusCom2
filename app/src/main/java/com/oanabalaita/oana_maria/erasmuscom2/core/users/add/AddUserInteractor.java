package com.oanabalaita.oana_maria.erasmuscom2.core.users.add;

import android.content.Context;
import android.support.annotation.NonNull;

import com.oanabalaita.oana_maria.erasmuscom2.R;
import com.oanabalaita.oana_maria.erasmuscom2.models.UserChat;
import com.oanabalaita.oana_maria.erasmuscom2.utils.Constants;
import com.oanabalaita.oana_maria.erasmuscom2.utils.SharedPrefUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Oana-Maria on 05/06/2017.
 */

public class AddUserInteractor implements AddUserContract.Interactor {

    private AddUserContract.OnUserDatabaseListener mOnUserDatabaseListener;

    public AddUserInteractor(AddUserContract.OnUserDatabaseListener onUserDatabaseListener) {
        this.mOnUserDatabaseListener = onUserDatabaseListener;
    }

    @Override
    public void addUserToDatabase(final Context context, FirebaseUser firebaseUser) {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        UserChat user = new UserChat(firebaseUser.getUid(),
                firebaseUser.getEmail(),
                new SharedPrefUtil(context).getString(Constants.ARG_FIREBASE_TOKEN));
        database.child(Constants.ARG_USERS)
                .child(firebaseUser.getUid())
                .setValue(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            mOnUserDatabaseListener.onSuccess(context.getString(R.string.user_successfully_added));
                        } else {
                            mOnUserDatabaseListener.onFailure(context.getString(R.string.user_unable_to_add));
                        }
                    }
                });
    }
}
