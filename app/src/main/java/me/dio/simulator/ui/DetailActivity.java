package me.dio.simulator.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.Objects;

import me.dio.simulator.databinding.ActivityDetailBinding;
import me.dio.simulator.domain.Match;

public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding binding;

    public static final String EXTRA_MATCH = "EXTRA_MATCH";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        loadMatchFromExtra();
    }

    private void loadMatchFromExtra() {
        Match match = getIntent().getExtras().getParcelable(EXTRA_MATCH);

        Glide.with(this).load(match.getPlace().getImage()).into(binding.ivPlace);
        getSupportActionBar().setTitle(match.getPlace().getName());

        binding.tvDescription.setText(match.getDescription());

        Glide.with(this).load(match.getHomeTeam().getImage()).into(binding.ivHomeTeam);
        binding.tvHomeTeamName.setText(match.getHomeTeam().getName());
        binding.rbHomeTeamStars.setRating(Float.valueOf(match.getHomeTeam().getStars()));
        if (match.getHomeTeam().getScore() != null) {
            binding.tvHomeTeamScore.setText(String.valueOf(match.getHomeTeam().getScore()));
        }

        Glide.with(this).load(match.getAwayTeam().getImage()).into(binding.ivAwayTeam);
        binding.tvAwayTeamName.setText(match.getAwayTeam().getName());
        binding.rbAwayTeamStars.setRating(Float.valueOf(match.getAwayTeam().getStars()));
        if (match.getAwayTeam().getScore() != null) {
            binding.tvAwayTeamScore.setText(String.valueOf(match.getAwayTeam().getScore()));
        }
    }
}
