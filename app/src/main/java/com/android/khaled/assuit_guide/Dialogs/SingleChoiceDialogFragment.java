package com.android.khaled.assuit_guide.Dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import java.util.List;

/**
 * Created by khaled on 03/09/16.
 */
public class SingleChoiceDialogFragment extends DialogFragment
{
    public static final String DATA = "items";

    public static final String SELECTED = "selected";

    public List<String> list;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        Resources res = getActivity().getResources();
        Bundle bundle = getArguments();

        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());

        dialog.setTitle("اختر رقم الهاتف");
        dialog.setPositiveButton("Cancel", new PositiveButtonClickListener());

        list = (List<String>)bundle.get(DATA);
        int position = bundle.getInt(SELECTED);

        CharSequence[] cs = list.toArray(new CharSequence[list.size()]);
        dialog.setSingleChoiceItems(cs, position, selectItemListener);

        return dialog.create();
    }

    class PositiveButtonClickListener implements DialogInterface.OnClickListener
    {
        @Override
        public void onClick(DialogInterface dialog, int which)
        {
            dialog.dismiss();
        }
    }

    DialogInterface.OnClickListener selectItemListener = new DialogInterface.OnClickListener()
    {
        @Override
        public void onClick(DialogInterface dialog, int which)
        {
            String Phone_Number = list.get(which);
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:"+Phone_Number));
            getActivity().startActivity(intent);
            dialog.dismiss();
        }
    };

}