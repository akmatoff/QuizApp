package com.insarindutries.quizapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME  = "QuizApp.db";
    private static final int DATABASE_VERSION = 4;

    private SQLiteDatabase db;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuizContract.QuestionsTable.TABLE_NAME + " ( " +
                QuizContract.QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract.QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_ANSWER_NUM + " INTEGER" +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.QuestionsTable.TABLE_NAME);
        onCreate(db);

    }

    private void fillQuestionsTable() {
        Question q1 = new Question("Сколько длились темные века?", "XII - IX", "XI - IX", "XI - X", 2);
        addQuestion(q1);
        Question q2 = new Question("События в 1194 - 1184 до н. э.", "Вторжение на Пелопоннес дорийских племен", "Нет правильного ответа", "Троянская война", 3);
        addQuestion(q2);
        Question q3 = new Question("Поход Дария I против скифов.", "514 г.", "524 г.", "510 г.", 1);
        addQuestion(q3);
        Question q4 = new Question("В каком году открыли олимпийские игры?", "866 г.", "776 г.", "756 г.", 2);
        addQuestion(q4);
        Question q5 = new Question("Правление Дария I в Персии.", "522 - 486", "552 - 468", "520 - 480", 1);
        addQuestion(q5);
        Question q6 = new Question("Формирование афинской морской державы", "Нет правильного ответа", "454", "554", 2);
        addQuestion(q6);
        Question q7 = new Question("Война Спарты с Персией", "389 - 394 гг.", "400 - 395 гг.", "399 - 394 гг.", 3);
        addQuestion(q7);
        Question q8 = new Question("Египетский поход Александра", "331 - 330 гг.", "Нет правильного ответа", "333 - 331 гг.", 2);
        addQuestion(q8);
        Question q9 = new Question("Годы жизни философа Сократа", "467 - 399 гг.", "477 - 399 гг.", "470 - 399 гг.", 3);
        addQuestion(q9);
        Question q10 = new Question("Годы жизни философа Платона.", "425 - 345 гг.", "427 - 347 гг.", "426 - 346 гг.", 2);
        addQuestion(q10);
    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuizContract.QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuizContract.QuestionsTable.COLUMN_ANSWER_NUM, question.getAsnwerNum());
        db.insert(QuizContract.QuestionsTable.TABLE_NAME, null, cv);
    }

    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuizContract.QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION3)));
                question.setAsnwerNum(c.getInt(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_ANSWER_NUM)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
}
