package com.example.zadanie_15;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Random;

public class ViewPagerFragment extends Fragment {

    private TextView tvPl;
    private EditText etEn;
    private Button button;
    private String color;
    private String textPl;
    private String textEn;
    private boolean correct;


    public ViewPagerFragment(String color, String textPl, String textEn) {
        super();
        this.color = color;
        this.textPl = textPl;
        this.textEn = textEn;
    }

    public boolean isCorrect() {
        return correct;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container,
                false);
        tvPl = (TextView) view.findViewById(R.id.text_pl);
        etEn = (EditText) view.findViewById(R.id.et_en);
        button = (Button) view.findViewById(R.id.button_odp);

        tvPl.setBackgroundColor(Color.parseColor(color));
        etEn.setBackgroundColor(Color.parseColor(color));

        tvPl.setText(textPl);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textEn.equals(etEn.getText().toString())) {
                    Toast.makeText(getActivity(), "Bravo!", Toast.LENGTH_LONG).show();
                    correct = true;
                } else {
                    correct = false;
                    Toast.makeText(getActivity(), "Try again!", Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }
}
