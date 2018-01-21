package com.foodback.foodback.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.foodback.foodback.R;

/**
 * Created by Foodback on 2017/2018.
 */

public class DialogReport extends AppCompatDialogFragment{

    private EditText editReport;
    private DialogReportListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.popup_report_comment, null);

        builder.setView(view)
                .setTitle("Denunciar Comentário")
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Denunciar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String report = editReport.getText().toString();
                        listener.applyfield(report);
                    }
                });

        editReport = view.findViewById(R.id.report_description);

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (DialogReportListener) context;
        } catch (Exception e) {
            throw new ClassCastException(context.toString() + "must implement DialogReportListener");
        }

    }

    public interface DialogReportListener {
        void applyfield(String editReport);
    }
}
