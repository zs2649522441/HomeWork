package fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.day1.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Lianxirenfragment extends Fragment {

    public Lianxirenfragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this
        View inflate = inflater.inflate(R.layout.fragment_lianxirenfragment, container, false);
        return inflate;
    }
}
