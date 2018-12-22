package com.example.sougataghosh.navstart;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

public class aboutActivity extends AppCompatActivity {

    Button goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        setExpandedStrings();

        goBack = findViewById(R.id.back_1);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(aboutActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setExpandedStrings() {

        String hisRSI = getResources().getString(R.string.aboutRSAMI);
        ExpandableTextView expandableTextView_1 = (ExpandableTextView) findViewById(R.id.hisRSI_ID);
        expandableTextView_1.setText(hisRSI);

        String affiliations = getResources().getString(R.string.affiliations);
        ExpandableTextView expandableTextView_2 = (ExpandableTextView) findViewById(R.id.affiliations_ID);
        expandableTextView_2.setText(affiliations);

        String membershipRules = getResources().getString(R.string.membershipRules);
        ExpandableTextView expandableTextView_3 = (ExpandableTextView) findViewById(R.id.membershipRules_ID);
        expandableTextView_3.setText(membershipRules);
    }

}

class ExpandableTextView extends android.support.v7.widget.AppCompatTextView {
    private static final int DEFAULT_TRIM_LENGTH = 80;
    private static final String ELLIPSIS = ".....";

    private CharSequence originalText;
    private CharSequence trimmedText;
    private BufferType bufferType;
    private boolean trim = true;
    private int trimLength;

    public ExpandableTextView(Context context) {
        this(context, null);
    }

    public ExpandableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ExpandableTextView);
        this.trimLength = typedArray.getInt(R.styleable.ExpandableTextView_trimLength, DEFAULT_TRIM_LENGTH);
        typedArray.recycle();

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                trim = !trim;
                setText();
                requestFocusFromTouch();
            }
        });
    }

    private void setText() {
        super.setText(getDisplayableText(), bufferType);
    }

    private CharSequence getDisplayableText() {
        return trim ? trimmedText : originalText;
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        originalText = text;
        trimmedText = getTrimmedText(text);
        bufferType = type;
        setText();
    }

    private CharSequence getTrimmedText(CharSequence text) {
        if (originalText != null && originalText.length() > trimLength) {
            return new SpannableStringBuilder(originalText, 0, trimLength + 1).append(ELLIPSIS);
        } else {
            return originalText;
        }
    }

    public CharSequence getOriginalText() {
        return originalText;
    }

    public void setTrimLength(int trimLength) {
        this.trimLength = trimLength;
        trimmedText = getTrimmedText(originalText);
        setText();
    }

    public int getTrimLength() {
        return trimLength;
    }
}

