package com.example.restaurant.FragmentApp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.restaurant.Adapter.AdapterDisplayDesk;
import com.example.restaurant.AddDeskActivity;
import com.example.restaurant.DAO.DeskDAO;
import com.example.restaurant.DTO.DeskDTO;
import com.example.restaurant.R;

import java.util.List;

public class DisplayDeskFragment extends Fragment {
    public static int REQUEST_CODE_ADD = 1111;
    GridView gvDisplayDesk;
    List<DeskDTO> listDesk;
    DeskDAO deskDAO;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.layout_display_desk,container,false);
        gvDisplayDesk = view.findViewById(R.id.gvDisplayDesk);
        deskDAO = new DeskDAO(getActivity());
        listDesk = deskDAO.getAllDesk();
        AdapterDisplayDesk adapterDisplayDesk = new AdapterDisplayDesk(getActivity(), R.layout.custom_layout_display_desk, listDesk);
        gvDisplayDesk.setAdapter(adapterDisplayDesk);
        adapterDisplayDesk.notifyDataSetChanged();

        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem iAddDesk = menu.add(1,R.id.iAddDesk,1,R.string.add_desk);
        iAddDesk.setIcon(R.drawable.thembanan);
        iAddDesk.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.iAddDesk:
                Intent intent= new Intent(getActivity(), AddDeskActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ADD);
                break;
        }
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_ADD){
            if(resultCode == Activity.RESULT_OK){
                boolean check = data.getBooleanExtra("result",false);
                if(check) {
                    Toast.makeText(getActivity(),"Them thanh cong",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getActivity(),"Them that bai",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
