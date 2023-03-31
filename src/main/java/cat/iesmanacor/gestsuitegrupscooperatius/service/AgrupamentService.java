package cat.iesmanacor.gestsuitegrupscooperatius.service;

import cat.iesmanacor.gestsuitegrupscooperatius.dto.AgrupamentDto;
import cat.iesmanacor.gestsuitegrupscooperatius.dto.GrupCooperatiuDto;
import cat.iesmanacor.gestsuitegrupscooperatius.model.Agrupament;
import cat.iesmanacor.gestsuitegrupscooperatius.model.GrupCooperatiu;
import cat.iesmanacor.gestsuitegrupscooperatius.repository.AgrupamentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class AgrupamentService {
    @Autowired
    private AgrupamentRepository agrupamentRepository;


    public List<AgrupamentDto> findAllByGrupCooperatiu(GrupCooperatiuDto grupCooperatiuDto){
        ModelMapper modelMapper = new ModelMapper();
        GrupCooperatiu grupCooperatiu = modelMapper.map(grupCooperatiuDto, GrupCooperatiu.class);
        return agrupamentRepository.findAllByGrupCooperatiu(grupCooperatiu).stream().map(agrupament->modelMapper.map(agrupament,AgrupamentDto.class)).collect(Collectors.toList());
    }

    public AgrupamentDto save(AgrupamentDto agrupamentDto) {
        ModelMapper modelMapper = new ModelMapper();
        Agrupament agrupament = modelMapper.map(agrupamentDto,Agrupament.class);
        Agrupament agrupamentSaved = agrupamentRepository.save(agrupament);
        return modelMapper.map(agrupamentSaved,AgrupamentDto.class);
    }

    public AgrupamentDto getAgrupamentById(Long id){
        ModelMapper modelMapper = new ModelMapper();
        //Ha de ser findById i no getById perquè getById és Lazy
        Agrupament agrupament = agrupamentRepository.findById(id).get();
        return modelMapper.map(agrupament,AgrupamentDto.class);
    }

    public void deleteByGrupCooperatiu(GrupCooperatiuDto grupCooperatiuDto){
        ModelMapper modelMapper = new ModelMapper();
        GrupCooperatiu grupCooperatiu = modelMapper.map(grupCooperatiuDto, GrupCooperatiu.class);
        agrupamentRepository.deleteAllByGrupCooperatiu(grupCooperatiu);
    }
}

