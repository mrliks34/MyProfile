package com.malik.myprofile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment; // Import ini

import java.util.ArrayList;
import java.util.List;

public class ProfileSelectionBottomSheet extends BottomSheetDialogFragment {

    // Constructor kosong (diperlukan untuk fragment)
    public ProfileSelectionBottomSheet() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate layout untuk fragment ini
        View view = inflater.inflate(R.layout.bottom_sheet_profile_selection, container, false);

        // Inisialisasi RecyclerView dan Adapternya
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_categories_white);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));

        List<Category> categories = new ArrayList<>();
        categories.add(new Category(getString(R.string.biodata_title), R.drawable.ic_biodata, "biodata"));
        categories.add(new Category(getString(R.string.pendidikan_title), R.drawable.ic_education, "pendidikan"));
        categories.add(new Category(getString(R.string.aktivitas_title), R.drawable.ic_activity, "aktivitas"));

        CategoryAdapter adapter = new CategoryAdapter(getContext(), categories);
        recyclerView.setAdapter(adapter);

        // Inisialisasi ImageView untuk media sosial dan set OnClickListener
        ImageView iconLinkedin = view.findViewById(R.id.icon_linkedin);
        ImageView iconGithub = view.findViewById(R.id.icon_github);
        ImageView iconInstagram = view.findViewById(R.id.icon_instagram);
        ImageView iconTwitter = view.findViewById(R.id.icon_twitter);

        iconLinkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSocialMediaLink("https://www.linkedin.com/in/yaronfanger");
            }
        });

        iconGithub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSocialMediaLink("https://github.com/yaronfanger");
            }
        });

        iconInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSocialMediaLink("https://www.instagram.com/yaronfanger_ig");
            }
        });

        iconTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSocialMediaLink("https://twitter.com/yaronfanger_x");
            }
        });

        // Contoh: Mengisi nama dan posisi dari string.xml
        TextView nameText = view.findViewById(R.id.name_text_selection_white);
        TextView positionText = view.findViewById(R.id.position_text_selection_white);
        nameText.setText(getString(R.string.nama_lengkap));
        positionText.setText(getString(R.string.posisi));


        return view;
    }

    // Metode untuk membuka link (tetap sama)
    private void openSocialMediaLink(String url) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getContext(), "Tidak dapat membuka link: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    // Karena ini BottomSheetDialogFragment, dia tidak punya onBackPressed override untuk transisi,
    // dia akan slide down otomatis ketika di-swipe ke bawah atau tap di luar area sheet.
}