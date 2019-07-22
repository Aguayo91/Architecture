package com.christian.aguayo.architecture.bd;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.christian.aguayo.architecture.model.Note;

@Database(entities = {Note.class}, version = 1, exportSchema = false)
public abstract class NoteDataBase extends RoomDatabase {

	private static NoteDataBase instance;
	public abstract NoteDao noteDAO();

	public static synchronized NoteDataBase getInstance(Context context) {
		if (instance == null) {
			instance = Room.databaseBuilder(context.getApplicationContext(),
					NoteDataBase.class, "note_database")
					.fallbackToDestructiveMigration()
					.addCallback(roomCallback)
					.build();
		}
		return instance;
	}

	private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
		@Override
		public void onCreate(@NonNull SupportSQLiteDatabase db) {
			super.onCreate(db);
			new PopulateDbAsynTask(instance).execute();

		}
	};

	private static class PopulateDbAsynTask extends AsyncTask<Void, Void, Void> {
		private NoteDao noteDao;

		private PopulateDbAsynTask(NoteDataBase db) {
			noteDao = db.noteDAO();

		}

		@Override
		protected Void doInBackground(Void... voids) {
			noteDao.insert(new Note("Titulo1", "Descripción1", 1));
			noteDao.insert(new Note("Titulo2", "Descripción2", 2));
			noteDao.insert(new Note("Titulo3", "Descripción3", 3));

			return null;
		}
	}
}
