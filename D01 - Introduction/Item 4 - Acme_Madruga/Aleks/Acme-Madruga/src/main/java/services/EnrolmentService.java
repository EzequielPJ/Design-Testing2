
package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.EnrolmentRepository;

@Service
@Transactional
public class EnrolmentService {

	@Autowired
	private EnrolmentRepository	enrolmentrepository;

}
