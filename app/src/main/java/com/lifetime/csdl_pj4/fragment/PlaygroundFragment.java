package com.lifetime.csdl_pj4.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lifetime.csdl_pj4.R;
import com.lifetime.csdl_pj4.adapter.PlaygroundAdapter;
import com.lifetime.csdl_pj4.model.Hotel;
import com.lifetime.csdl_pj4.model.Playground;
import com.lifetime.csdl_pj4.viewmodel.PlaceDetailViewModel;

import java.util.ArrayList;
import java.util.List;

public class PlaygroundFragment extends Fragment {

    private View view;

    private String searchKey;

    public static PlaygroundFragment instance(String searchKey) {
        PlaygroundFragment playgroundFragment = new PlaygroundFragment();
        playgroundFragment.searchKey = searchKey;
        return playgroundFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.playground_fragment, container, false);

            PlaceDetailViewModel placeDetailViewModel = ViewModelProviders.of(this).get(PlaceDetailViewModel.class);

            placeDetailViewModel.getPlaygroundListByPlaceName().observe(this, new Observer<List<Playground>>() {
                @Override
                public void onChanged(List<Playground> playgrounds) {
                    initView(playgrounds);
                }
            });

            placeDetailViewModel.searchPlaygroundListByPlaceName(searchKey);

        }

        return view;
    }

    private void initView(List<Playground> playgrounds) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_playground);
        recyclerView.hasFixedSize();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<Playground> playgroundInfors = new ArrayList<>(playgrounds);

        PlaygroundAdapter adapter = new PlaygroundAdapter(playgroundInfors);
        recyclerView.setAdapter(adapter);
    }
}
