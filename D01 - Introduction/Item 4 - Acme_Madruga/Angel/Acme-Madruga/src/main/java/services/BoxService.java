
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.BoxRepository;
import security.LoginService;
import domain.Actor;
import domain.Box;
import domain.Message;

@Service
@Transactional
public class BoxService {

	@Autowired(required = false)
	private Validator		validator;

	@Autowired
	private BoxRepository	boxRepository;


	//Queries del repo de box
	public Box getActorEntryBox(final int id) {
		return this.boxRepository.getActorEntryBox(id);
	}

	public Box getActorSpamBox(final int id) {
		return this.boxRepository.getActorSpamBox(id);
	}

	public Box getActorSendedBox(final int id) {
		return this.boxRepository.getActorSendedBox(id);
	}

	public Box getActorTrashBox(final int id) {
		return this.boxRepository.getActorTrashBox(id);
	}

	public Box getActorNotificationBox(final int id) {
		return this.boxRepository.getActorNotificationBox(id);
	}

	public Box getActorOtherBox(final int id, final int other) {
		return this.boxRepository.getActorOtherBox(id, other);
	}

	public Collection<Box> getBoxesFromUserAccount(final int id) {
		return this.boxRepository.getBoxesFromUserAccount(id);
	}

	public Collection<Box> getBoxesFromActorNoSystem(final int id) {
		return this.boxRepository.getBoxesFromActorNoSystem(id);
	}

	public Actor getActorByUserAccount(final int id) {
		return this.boxRepository.getActorByUserAccount(id);
	}

	public Collection<Actor> findAllActorsSystem(final int id) {
		return this.boxRepository.findAllActorsSystem(id);
	}
	//findOne
	public Box findOne(final int id) {
		final Box b;
		b = this.boxRepository.findOne(id);
		Assert.isTrue(this.boxRepository.getActorByUserAccount(LoginService.getPrincipal().getId()).getBoxes().contains(b));
		return b;
	}
	//create
	public Box createBox() {
		final Box b;
		b = new Box();
		b.setFromSystem(false);
		b.setName("");
		b.setMessage(new ArrayList<Message>());
		return b;
	}
	//update
	//	public Box updateBox(final int id) {
	//		UserAccount user;
	//		user = LoginService.getPrincipal();
	//		Actor a;
	//		a = this.boxRepository.getActorByUserAccount(user.getId());
	//
	//		Box box;
	//		box = this.boxRepository.findOne(id);
	//	}
	//save
	public Box save(final Box b) {
		Box saved;
		saved = this.boxRepository.save(b);
		final Actor a;
		a = this.boxRepository.getActorByUserAccount(LoginService.getPrincipal().getId());
		Collection<Box> colbox;
		colbox = a.getBoxes();
		colbox.add(saved);
		a.setBoxes(colbox);
		return saved;
	}
	//save subbox
	public Box saveSubBox(final Box b, final Box parent) {
		Box saved;
		saved = this.boxRepository.save(b);
		final Actor a;
		a = this.boxRepository.getActorByUserAccount(LoginService.getPrincipal().getId());
		Collection<Box> colbox;
		colbox = a.getBoxes();
		colbox.add(saved);
		a.setBoxes(colbox);

		Collection<Box> colboxp;
		colboxp = parent.getBoxes();
		System.out.println("parent" + parent);
		colboxp.add(saved);
		parent.setBoxes(colboxp);

		return saved;
	}
	//delete
	public void delete(final Box b) {
		Assert.isTrue(!b.isFromSystem());

		final Actor a;
		a = this.boxRepository.getActorByUserAccount(LoginService.getPrincipal().getId());

		Collection<Message> colmes;
		colmes = b.getMessage();
		//elimina las boxes que tienen los mensajes ya que es bidireccional
		for (final Message m : colmes)
			if (m.getBox().contains(b)) {
				final Collection<Box> boxes = m.getBox();
				boxes.remove(b);
				m.setBox(boxes);
			}
		//setteamos los mensajes de la box a vac�o
		colmes.clear();
		b.setMessage(colmes);
		//eliminamos la caja al actor que la tiene
		Collection<Box> boxesActor;
		boxesActor = a.getBoxes();
		boxesActor.remove(b);
		a.setBoxes(boxesActor);

		this.boxRepository.delete(b);

	}

	public Box reconstruct(final Box box, final BindingResult binding) {
		Box result;

		if (box.getId() == 0)
			result = box;
		else {
			result = this.boxRepository.findOne(box.getId());

			result.setName(box.getName());
			result.setMessage(box.getMessage());

			this.validator.validate(result, binding);
		}
		return result;
	}
}
