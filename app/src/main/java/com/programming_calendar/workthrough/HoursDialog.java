package com.programming_calendar.workthrough;

import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.SeekBar;
import android.widget.ArrayAdapter;
import android.content.Context;

public class HoursDialog extends Dialog
{
    LayoutInflater inflater;
    View layout;

    Spinner spinProg;
    final SeekBar sbarHours;
    final TextView nrHours;
    Button btnConfirm;

    ArrayAdapter<String> spinProgArrayAdapter;

    public HoursDialog (Context context) {
        super(context);
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layout = inflater.inflate(R.layout.hours_dialog, (ViewGroup) findViewById(R.id.hoursDialogRoot));
        this.setContentView(layout);

        spinProg = (Spinner) layout.findViewById(R.id.spinProg);
        sbarHours = (SeekBar) layout.findViewById(R.id.sbarHours);
        nrHours = (TextView) layout.findViewById(R.id.nrHours);
        btnConfirm = (Button) layout.findViewById(R.id.btnConfirm);

        spinProgArrayAdapter =
                new ArrayAdapter<String>(context,
                        android.R.layout.simple_spinner_item,
                        context.getResources().getStringArray(R.array.programming_array));
        spinProgArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinProg.setAdapter(spinProgArrayAdapter);

        sbarHours.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onStopTrackingTouch(SeekBar seekBar){

            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar){

            }
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                int hoursSpent = sbarHours.getProgress();
                nrHours.setText("" + hoursSpent);
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                dismiss();
                v.setBackground(new ColorBarDrawable(new int[]{0x00000000,0xff7070ff,0xffffdd66},new int[]{6,3,3}));
            }
        });

        show();
    }
}