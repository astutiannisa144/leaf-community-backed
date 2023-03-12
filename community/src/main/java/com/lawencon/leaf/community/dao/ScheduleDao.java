package com.lawencon.leaf.community.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.base.ConnHandler;
import com.lawencon.leaf.community.model.Schedule;

@Repository
public class ScheduleDao extends BaseDao<Schedule> {

	@SuppressWarnings("unchecked")
	@Override
	public List<Schedule> getAll() {
		final StringBuilder str = new StringBuilder();
		str.append("SELECT * ");
		str.append("FROM t_schedule");

		final List<Schedule> schedules = ConnHandler.getManager().createNativeQuery(str.toString(), Schedule.class).getResultList();
		return schedules;
	}

	@Override
	public Optional<Schedule> getByIdAndDetach(String id) {
		return Optional.ofNullable(super.getByIdAndDetach(Schedule.class, id));
	}

	@Override
	public Optional<Schedule> getById(String id) {

		return Optional.ofNullable(super.getById(Schedule.class, id));

	}

}
