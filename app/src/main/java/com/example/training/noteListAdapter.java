package com.example.training;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
public class noteListAdapter extends ArrayAdapter<note> {
    private Context mContext;
    private List<note> str;


    int mResource;
    public noteListAdapter(Context context, int resource, List<note> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        str = objects;


    }


    @NonNull
    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        String title = getItem(position).getTitle();
        String text =  getItem(position).getText();

        note kek = new note(title,text);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent,false);
        TextView tvName = (TextView) convertView.findViewById(R.id.textView5);
        TextView tvText = (TextView) convertView.findViewById(R.id.textView6);
        ImageButton deleteImageView = (ImageButton) convertView.findViewById(R.id.deleteButton);






        tvName.setText(title);
        tvText.setText(text);
        deleteImageView.setId(position);
        deleteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("!", String.valueOf(deleteImageView.getId()));
                FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(title).removeValue();



            }
        });
        return convertView;
    }
}
