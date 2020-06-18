package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.demoapp.DlActivity;
import com.example.demoapp.R;


public class PersonFragment extends Fragment {

    private TextView mDl_but;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.person, null);
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {

        mDl_but = inflate.findViewById(R.id.dl_but);
        mDl_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), DlActivity.class));
            }
        });

    }
}
