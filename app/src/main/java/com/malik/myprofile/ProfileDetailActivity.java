package com.malik.myprofile;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import android.animation.ObjectAnimator;

public class ProfileDetailActivity extends AppCompatActivity {

    private View biodataSection;
    private View pendidikanSection;
    private View aktivitasSection;
    private View skillsSection;

    private static final String PREFS_NAME = "ThemePrefs";
    private static final String PREF_DARK_MODE = "dark_mode";
    private ImageView themeIconDetail;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean isDarkMode = prefs.getBoolean(PREF_DARK_MODE, false);
        setAppTheme(isDarkMode);

        setContentView(R.layout.activity_profile_detail);

        themeIconDetail = findViewById(R.id.theme_icon_detail);
        updateThemeIcon(isDarkMode);

        themeIconDetail.setOnClickListener(v -> {
            boolean newDarkModeState = !prefs.getBoolean(PREF_DARK_MODE, false);
            prefs.edit().putBoolean(PREF_DARK_MODE, newDarkModeState).apply();
            setAppTheme(newDarkModeState);
            animateThemeIconChange(newDarkModeState);
            recreate();
        });

        ImageView iconBack = findViewById(R.id.icon_back_detail);
        iconBack.setOnClickListener(v -> onBackPressed());

        TextView detailTitle = findViewById(R.id.detail_title);

        biodataSection = findViewById(R.id.included_biodata_section);
        pendidikanSection = findViewById(R.id.included_pendidikan_section);
        aktivitasSection = findViewById(R.id.included_aktivitas_section);
        skillsSection = findViewById(R.id.included_skill_section);

        biodataSection.setVisibility(View.GONE);
        pendidikanSection.setVisibility(View.GONE);
        aktivitasSection.setVisibility(View.GONE);
        skillsSection.setVisibility(View.GONE);

        String detailType = getIntent().getStringExtra("detail_type");

        if ("biodata".equals(detailType)) {
            detailTitle.setText(getString(R.string.biodata_title));
            biodataSection.setVisibility(View.VISIBLE);
            populateBiodata();

        } else if ("pendidikan".equals(detailType)) {
            detailTitle.setText(getString(R.string.pendidikan_title));
            pendidikanSection.setVisibility(View.VISIBLE);
            populatePendidikan();

        } else if ("aktivitas".equals(detailType)) {
            detailTitle.setText(getString(R.string.aktivitas_title));
            aktivitasSection.setVisibility(View.VISIBLE);
            populateAktivitas();

        } else if ("skill".equals(detailType)) {
            detailTitle.setText(getString(R.string.skills_title));
            skillsSection.setVisibility(View.VISIBLE);
            populateSkills();
        }
    }


    private void populateBiodata() {
        TextView biodataNamaLengkap = biodataSection.findViewById(R.id.biodata_nama_lengkap);
        TextView biodataNIM = biodataSection.findViewById(R.id.biodata_nim);
        TextView biodataPosisi = biodataSection.findViewById(R.id.biodata_posisi);
        TextView biodataAlamat = biodataSection.findViewById(R.id.biodata_alamat);
        TextView biodataEmail = biodataSection.findViewById(R.id.biodata_email);
        TextView biodataTelepon = biodataSection.findViewById(R.id.biodata_telepon);
        TextView biodataTentangSaya = biodataSection.findViewById(R.id.biodata_tentang_saya);

        biodataNamaLengkap.setText(getString(R.string.nama_lengkap));
        biodataNIM.setText(getString(R.string.nim));
        biodataPosisi.setText(getString(R.string.posisi));
        biodataAlamat.setText(getString(R.string.alamat));
        biodataEmail.setText(getString(R.string.email));
        biodataTelepon.setText(getString(R.string.telepon));
        biodataTentangSaya.setText(getString(R.string.tentang_saya));
    }

    private void populatePendidikan() {
        TextView pendidikanSdNama = pendidikanSection.findViewById(R.id.pendidikan_sd_nama);
        TextView pendidikanSdTahun = pendidikanSection.findViewById(R.id.pendidikan_sd_tahun);
        TextView pendidikanSmpNama = pendidikanSection.findViewById(R.id.pendidikan_smp_nama);
        TextView pendidikanSmpTahun = pendidikanSection.findViewById(R.id.pendidikan_smp_tahun);
        TextView pendidikanSmaNama = pendidikanSection.findViewById(R.id.pendidikan_sma_nama);
        TextView pendidikanSmaTahun = pendidikanSection.findViewById(R.id.pendidikan_sma_tahun);
        TextView pendidikanKuliahNama = pendidikanSection.findViewById(R.id.pendidikan_kuliah_nama);
        TextView pendidikanKuliahJurusan = pendidikanSection.findViewById(R.id.pendidikan_kuliah_jurusan);
        TextView pendidikanKuliahTahun = pendidikanSection.findViewById(R.id.pendidikan_kuliah_tahun);

        pendidikanSdNama.setText(getString(R.string.sd_nama));
        pendidikanSdTahun.setText(getString(R.string.sd_tahun));
        pendidikanSmpNama.setText(getString(R.string.smp_nama));
        pendidikanSmpTahun.setText(getString(R.string.smp_tahun));
        pendidikanSmaNama.setText(getString(R.string.sma_nama));
        pendidikanSmaTahun.setText(getString(R.string.sma_tahun));
        pendidikanKuliahNama.setText(getString(R.string.kuliah_nama));
        pendidikanKuliahJurusan.setText(getString(R.string.kuliah_jurusan));
        pendidikanKuliahTahun.setText(getString(R.string.kuliah_tahun));
    }

    private void populateAktivitas() {
        TextView aktivitasDeskripsiText = aktivitasSection.findViewById(R.id.aktivitas_deskripsi_text);
        TextView jadwalJamKerja = aktivitasSection.findViewById(R.id.jadwal_jam_kerja);
        TextView jadwalJamKerja0515 = aktivitasSection.findViewById(R.id.jadwal_jam_kerja05_15);
        TextView jadwalJamKerja0600 = aktivitasSection.findViewById(R.id.jadwal_jam_kerja06_00);
        TextView jadwalJamKerja0630 = aktivitasSection.findViewById(R.id.jadwal_jam_kerja06_30);
        TextView jadwalJamKerja0700 = aktivitasSection.findViewById(R.id.jadwal_jam_kerja07_00);
        TextView jadwalJamKerja0800 = aktivitasSection.findViewById(R.id.jadwal_jam_kerja08_00);
        TextView jadwalJamKerja1200 = aktivitasSection.findViewById(R.id.jadwal_jam_kerja12_00);
        TextView jadwalJamKerja1230 = aktivitasSection.findViewById(R.id.jadwal_jam_kerja12_30);
        TextView jadwalJamKerja1530 = aktivitasSection.findViewById(R.id.jadwal_jam_kerja15_30);
        TextView jadwalJamKerja1545 = aktivitasSection.findViewById(R.id.jadwal_jam_kerja15_45);
        TextView jadwalJamKerja1645 = aktivitasSection.findViewById(R.id.jadwal_jam_kerja16_45);
        TextView jadwalJamKerja1730 = aktivitasSection.findViewById(R.id.jadwal_jam_kerja17_30);
        TextView jadwalJamKerja1800 = aktivitasSection.findViewById(R.id.jadwal_jam_kerja18_00);
        TextView jadwalJamKerja1820 = aktivitasSection.findViewById(R.id.jadwal_jam_kerja18_20);
        TextView jadwalJamKerja2140 = aktivitasSection.findViewById(R.id.jadwal_jam_kerja21_40);
        TextView jadwalJamKerja21402 = aktivitasSection.findViewById(R.id.jadwal_jam_kerja21_40_2);
        TextView jadwalJamKerja2300 = aktivitasSection.findViewById(R.id.jadwal_jam_kerja23_00);
        TextView jadwalJamKerja2315 = aktivitasSection.findViewById(R.id.jadwal_jam_kerja23_15);
        TextView jadwalJamKerja23152 = aktivitasSection.findViewById(R.id.jadwal_jam_kerja23_15_2);
        TextView jadwalJamKerja2330 = aktivitasSection.findViewById(R.id.jadwal_jam_kerja23_30);
        TextView jadwalJamKerja0200 = aktivitasSection.findViewById(R.id.jadwal_jam_kerja02_00);

        aktivitasDeskripsiText.setText(getString(R.string.aktivitas_deskripsi));
        jadwalJamKerja.setText(getString(R.string.jam_kerja));
        jadwalJamKerja0515.setText(getString(R.string.jam_kerja05_15));
        jadwalJamKerja0600.setText(getString(R.string.jam_kerja06_00));
        jadwalJamKerja0630.setText(getString(R.string.jam_kerja06_30));
        jadwalJamKerja0700.setText(getString(R.string.jam_kerja07_00));
        jadwalJamKerja0800.setText(getString(R.string.jam_kerja08_00));
        jadwalJamKerja1200.setText(getString(R.string.jam_kerja12_00));
        jadwalJamKerja1230.setText(getString(R.string.jam_kerja12_30));
        jadwalJamKerja1530.setText(getString(R.string.jam_kerja15_30));
        jadwalJamKerja1545.setText(getString(R.string.jam_kerja15_45));
        jadwalJamKerja1645.setText(getString(R.string.jam_kerja16_45));
        jadwalJamKerja1730.setText(getString(R.string.jam_kerja17_30));
        jadwalJamKerja1800.setText(getString(R.string.jam_kerja18_00));
        jadwalJamKerja1820.setText(getString(R.string.jam_kerja18_20));
        jadwalJamKerja2140.setText(getString(R.string.jam_kerja21_40));
        jadwalJamKerja21402.setText(getString(R.string.jam_kerja21_40_2));
        jadwalJamKerja2300.setText(getString(R.string.jam_kerja23_00));
        jadwalJamKerja2315.setText(getString(R.string.jam_kerja23_15));
        jadwalJamKerja23152.setText(getString(R.string.jam_kerja23_15_2));
        jadwalJamKerja2330.setText(getString(R.string.jam_kerja23_30));
        jadwalJamKerja0200.setText(getString(R.string.jam_kerja02_00));
    }

    private void populateSkills(){
        TextView skillWebDevFrontend = skillsSection.findViewById(R.id.skills_web_dev_frontend);
        TextView skillMobileDevAndroid = skillsSection.findViewById(R.id.skills_mobile_dev_android);
        TextView skillsAdmin = skillsSection.findViewById(R.id.skills_admin);
        TextView skillDatabase = skillsSection.findViewById(R.id.skills_database);
        TextView skillProblemSolving = skillsSection.findViewById(R.id.skills_problem_solving);
        TextView skillTeamwork = skillsSection.findViewById(R.id.skills_teamwork);
        TextView skillFastLearner = skillsSection.findViewById(R.id.skills_fast_learner);

        skillWebDevFrontend.setText(getString(R.string.skills_web_dev_frontend));
        skillMobileDevAndroid.setText(getString(R.string.skills_mobile_dev_android));
        skillsAdmin.setText(getString(R.string.skills_admin));
        skillDatabase.setText(getString(R.string.skills_database));
        skillProblemSolving.setText(getString(R.string.skills_problem_solving));
        skillTeamwork.setText(getString(R.string.skills_teamwork));
        skillFastLearner.setText(getString(R.string.skills_fast_learner));
    }


    private void setAppTheme(boolean isDarkMode) {
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    private void updateThemeIcon(boolean isDarkMode) {
        if (themeIconDetail != null) {
            if (isDarkMode) {
                themeIconDetail.setImageResource(R.drawable.ic_sun);
            } else {
                themeIconDetail.setImageResource(R.drawable.ic_moon);
            }
            themeIconDetail.clearColorFilter();
        }
    }

    private void animateThemeIconChange(boolean newDarkModeState) {
        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(themeIconDetail, "alpha", 1f, 0f);
        fadeOut.setDuration(150);

        fadeOut.addListener(new android.animation.AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(android.animation.Animator animation) {
                updateThemeIcon(newDarkModeState);
                ObjectAnimator fadeIn = ObjectAnimator.ofFloat(themeIconDetail, "alpha", 0f, 1f);
                fadeIn.setDuration(150);
                fadeIn.start();
            }
        });
        fadeOut.start();
    }
}