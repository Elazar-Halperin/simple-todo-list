package com.example.todoapp;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class AddDialogFragment extends DialogFragment {

    EditText et_missionText;
    FloatingActionButton fab_addMission;
    InputMethodManager imm;
    RecyclerView rv_mission;
    List<MissionModel> missionModelList;
    Context context;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // Dialog dialog = new Dialog(getActivity());
        Dialog dialog = new Dialog(getActivity(), R.style.CustomDatePickerDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // must be called before set content
        dialog.setContentView(R.layout.add_mission_dialog);
        dialog.setCanceledOnTouchOutside(true);

        // Set the width to the screen width, close to the bottom of the screen.
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);

        // set view for using the findViewById() function

        // initialize the items and the variables
        et_missionText = dialog.findViewById(R.id.et_missionText);
        fab_addMission = dialog.findViewById(R.id.fab_addMission);
        context = (MainActivity) getActivity();
        rv_mission = getActivity().findViewById(R.id.rv_missions);
        missionModelList = new ArrayList<>();

        // disable fab
        fab_addMission.setEnabled(false);
        fab_addMission.setColorFilter(getResources().getColor(R.color.add_button_background_gray));

        // work with the recycler view
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        MissionAdapter missionAdapter = new MissionAdapter(missionModelList, context);
        rv_mission.setLayoutManager(layoutManager);
        rv_mission.setAdapter(missionAdapter);


        et_missionText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s == null || s.toString().isEmpty()) {
                    fab_addMission.setEnabled(false);
                    fab_addMission.setColorFilter(getResources().getColor(R.color.add_button_background_gray));
                } else {
                    fab_addMission.setEnabled(true);
                    fab_addMission.setColorFilter(getResources().getColor(R.color.white));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        fab_addMission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MissionModel mission = new MissionModel(-1, false, et_missionText.getText().toString());
                missionModelList.add(mission);
                missionAdapter.notifyItemInserted(missionModelList.size() - 1);
                et_missionText.setText("");
            }
        });

        return dialog;
    }

    @Override
    public void onResume() {
        super.onResume();
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
        imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        //imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

    }

}
