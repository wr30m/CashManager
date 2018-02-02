package com.example.kirazavrik.cashmanager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class AddBalanceDialogFragment extends DialogFragment {

    public interface EditAddBalanceFragment {
        void onFinishEditAddBalanceFragment(String inputText);
    }

    public EditAddBalanceFragment listener;

    public static final String NEW_BALANCE = "balance";

    private EditText editText;
    private Button okButton;

    public AddBalanceDialogFragment() {}

    public static AddBalanceDialogFragment newInstance() {
        AddBalanceDialogFragment frag = new AddBalanceDialogFragment();
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.change_balance_dialog, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editText = (EditText) view.findViewById(R.id.changeBalanceField);
        editText.requestFocus();

        okButton = (Button) view.findViewById(R.id.okBtn);
        okButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                listener.onFinishEditAddBalanceFragment(editText.getText().toString());
                dismiss();
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (EditAddBalanceFragment) context;
        } catch (ClassCastException e) {
            throw new ClassCastException("must implement interface");
        }
    }
}
