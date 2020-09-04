package com.stackroute.keepnote.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.stackroute.keepnote.model.Note;
import org.springframework.beans.factory.annotation.Autowired;

/*
 * This class is implementing the NoteDAO interface. This class has to be annotated with @Repository
 * annotation.
 * @Repository - is an annotation that marks the specific class as a Data Access Object, thus 
 * 				 clarifying it's role.
 * @Transactional - The transactional annotation itself defines the scope of a single database 
 * 					transaction. The database transaction happens inside the scope of a persistence 
 * 					context.  
 * */

public class NoteDAOImpl implements NoteDAO {

	/*
	 * Autowiring should be implemented for the SessionFactory.
	 */
	@Autowired SessionFactory sessionFactory;
	public NoteDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/*
	 * Save the note in the database(note) table.
	 */

	public boolean saveNote(Note note) {
		this.sessionFactory.getCurrentSession().save(note);
		return true;

	}

	/*
	 * Remove the note from the database(note) table.
	 */

	public boolean deleteNote(int noteId) {
		if(getNoteById(noteId)!=null)
		{
			this.sessionFactory.getCurrentSession().delete(getNoteById(noteId));
			return true;
		}
		else
		{
			return  false;
		}

	}

	/*
	 * retrieve all existing notes sorted by created Date in descending
	 * order(showing latest note first)
	 */
	public List<Note> getAllNotes() {

		List result = getSessionFactory().getCurrentSession().createQuery("FROM Note note ORDER BY note.createdAt DESC").getResultList();
		return result;

	}

	/*
	 * retrieve specific note from the database(note) table
	 */
	public Note getNoteById(int noteId) {

		Note note = sessionFactory.getCurrentSession().get(Note.class,noteId);
		return note;

	}

	/* Update existing note */

	public boolean UpdateNote(Note note) {

		if(getNoteById(note.getNoteId())!=null)
		{
		sessionFactory.getCurrentSession().update(note);
			return true;
		}
		else
		{
			return false;
		}

	}

}
