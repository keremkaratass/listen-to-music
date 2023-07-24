package com.atmosware.listentomusic.business.concretes;

import com.atmosware.listentomusic.business.abstracts.GenreService;
import com.atmosware.listentomusic.business.dto.requests.create.CreateGenreRequest;
import com.atmosware.listentomusic.business.dto.requests.update.UpdateGenreRequest;
import com.atmosware.listentomusic.business.dto.responses.create.CreateGenreResponse;
import com.atmosware.listentomusic.business.dto.responses.get.GetGenreResponse;
import com.atmosware.listentomusic.business.dto.responses.get.all.GetAllGenresResponse;
import com.atmosware.listentomusic.business.dto.responses.update.UpdateGenreResponse;
import com.atmosware.listentomusic.entities.Genre;
import com.atmosware.listentomusic.repository.GenreRepository;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GenreManager implements GenreService {

    private final GenreRepository repository;
    private final ModelMapper mapper;

    @Override
    public List<GetAllGenresResponse> getAll() {
        var genres= repository.findAll();
        return genres.stream().map(genre -> mapper.map(genre, GetAllGenresResponse.class)).toList();
    }

    @Override
    public GetGenreResponse getById(UUID id) {
        var genre= repository.findById(id).orElseThrow();
        return mapper.map(genre,GetGenreResponse.class);
    }

    @Override
    public CreateGenreResponse add(CreateGenreRequest request) {
        var genre= mapper.map(request, Genre.class);
        genre.setId(UUID.randomUUID());
        repository.save(genre);
        return mapper.map(genre, CreateGenreResponse.class);

    }

    @Override
    public UpdateGenreResponse update(UUID id, UpdateGenreRequest request) {
        var genre= mapper.map(request, Genre.class);
        genre.setId(id);
        repository.save(genre);
        return mapper.map(genre, UpdateGenreResponse.class);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
