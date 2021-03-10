package com.example.consultancy.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.consultancy.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DocumentDetailActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tvTopic)
    TextView tvTopic;
    @BindView(R.id.card2)
    CardView card2;
    @BindView(R.id.tvAfteroffer)
    TextView tvAfteroffer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_detail);
        ButterKnife.bind(this);

        tvTopic.setText("FOR OFFER LETTER\n" +
                "     \"1) Academic document (from 12th level); not less than 55%\n" +
                "     \"2) IELTS SCORE (Overall 6.0 not band less then 5.5\n" +
                "     \"3) Applicant passport\n" +
                "     \"4) Statement of Purpose (SOP)\n" +
                "     \"5) GAP Evidence\n" +
                "     \"6) Application form\n" +
                "AFTER OFFER LETTER\n" +
                "    \"Genuine Temporary Entrant (GTE) +\n" +
                "    \"1) Offer letter Acceptance\n" +
                "    \"2) VDC Document\n" +
                "       \" a) Annual Income Certificate\n" +
                "       \" b) Tas celearance\n" +
                "       \" c) Relationship certificate\n" +
                "       \" d) Address verification\n" +
                "       \" e) Birth certificate\n" +
                "    \"3) Property Valuation\n" +
                "    \"4) Citizenship Certificate (who are involved in Relationship and Applicant)\n" +
                "    \"5) Education loan/self-money/evidence\n" +
                "    \"6) Police report\n" +
                "    \"7) GTE SOP/SSVF/CHECKLIST\n" +
                "    \"8) Affidavit of support\n" +
                "    \"9) Notarized all Academic Document\n" +
                "    \"10) IELTS Notarized\n" +
                "    \"11) Passport Notarized all Paper\n" +
                "    \"12) Medical Date + NO objection letter (NOC)\n" +
                "    \"13) Bank Account from (A level) Bank)");


    }
}
