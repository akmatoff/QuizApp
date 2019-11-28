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
    private static final int DATABASE_VERSION = 5;

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
                QuizContract.QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
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
        Question q1 = new Question("Основу древнего римского народа составляли", "римляне, сабины, эллины", "римляне, греки, этруски ", "римляне, сабины, этруски", "римляне, сабины, славяне", 3);
        addQuestion(q1);
        Question q2 = new Question("Языком римской цивилизации становится", "арабский", "римский", "греческий", "латынь", 4);
        addQuestion(q2);
        Question q3 = new Question("Кого римляне называли «варварами»?", "племена не знающие латинского или греческого языка", "жестоких императоров", "всех, кто проживал вне города", "всех", 1);
        addQuestion(q3);
        Question q4 = new Question("На чем писались древнерусские письма , документы?", "Береста", "Папирус", "Пергамент", "Лыко", 1);
        addQuestion(q4);
        Question q5 = new Question("Как в Древней Руси назывался княжеский престол?", "Стол", "Седалище ", "Держава", "Стуло", 1);
        addQuestion(q5);
        Question q6 = new Question("Начиная с какого времени в Китае начала править династия Тан?", " С начала седьмого века", "С начала шестого века ", "С конца восьмого века ", "С конца седьмого века", 1);
        addQuestion(q6);
        Question q7 = new Question("Какие кочевники начали завование Китая в начале XIII века?", "Скифы", "Тюрки", "Монголы", "Авары ", 3);
        addQuestion(q7);
        Question q8 = new Question("Столица монгольской державы", "Самарканд", "Пекин", "Сарай-Бату", "Каракорум", 3);
        addQuestion(q8);
        Question q9 = new Question("Что было изобретено в средневековом Китае?", "Печатный станок ", "Изготовление фарфора", "Боевая колесница", "Все варианты верны", 2);
        addQuestion(q9);
        Question q10 = new Question("В период какой династии средневековый Китай достиг наибольших размеров?", "Мин", "Тан", "Цинь", "Сун", 2);
        addQuestion(q10);
        Question q11 = new Question("Сколько длились темные века?", "XII - IX", "XI - IX", "XI - X", "XII - X", 2);
        addQuestion(q11);
        Question q12 = new Question("События в 1194 - 1184 до н. э.", "Вторжение на Пелопоннес дорийских племен", "Нет правильного ответа", "Троянская война", "Вторжение на Пелопоннес дорийских племен", 3);
        addQuestion(q12);
        Question q13 = new Question("Поход Дария I против скифов.", "514 г.", "524 г.", "510 г.", "526 г.", 1);
        addQuestion(q13);
        Question q14 = new Question("В каком году открыли олимпийские игры?", "866 г.", "776 г.", "756 г.", "856 г.", 2);
        addQuestion(q14);
        Question q15 = new Question("Правление Дария I в Персии", "522-486 гг.", "552-468 гг.", "520-480 гг.", "523-481 гг.", 1);
        addQuestion(q15);
        Question q16 = new Question("Год формирования афинской морской державы", "Нет правильного ответа", "454 г.", "554 г.", "523 г.", 2);
        addQuestion(q16);
        Question q17 = new Question("Война Спарты с Персией", "389-394 гг.", "400-395 гг.", "399-394 гг.", "390-394 гг.", 3);
        addQuestion(q17);
        Question q18 = new Question("Египетский поход Александра", "330-331 гг.", "Нет првильного ответа", "331-333 гг.", "331-342 гг.", 2);
        addQuestion(q18);
        Question q19 = new Question("Годы жизни философа Сократа", "399-467 гг.", "399-3477 гг.", "399-470 гг.", "399-481 гг.", 3);
        addQuestion(q19);
        Question q20 = new Question("Даты жизни философа Платона", "345-425 гг.", "347-427 гг.", "346-426 гг.", "346-430 гг.", 2);
        addQuestion(q20);

    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuizContract.QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION4, question.getOption4());
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
                question.setOption4(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION4)));
                question.setAsnwerNum(c.getInt(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_ANSWER_NUM)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
}
