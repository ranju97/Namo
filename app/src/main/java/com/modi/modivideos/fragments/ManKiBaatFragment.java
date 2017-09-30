package com.modi.modivideos.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.modi.modivideos.callback.BaseInterface;
import com.modi.modivideos.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link ManKiBaatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ManKiBaatFragment extends Fragment {

    private BaseInterface mListener;

    public ManKiBaatFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ManKiBaatFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ManKiBaatFragment newInstance() {
        ManKiBaatFragment fragment = new ManKiBaatFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_man_ki_baat, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseInterface) {
            mListener = (BaseInterface) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement BaseInterface");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
