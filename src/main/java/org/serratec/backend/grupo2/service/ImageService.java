package org.serratec.backend.grupo2.service;

import java.io.IOException;
import java.util.Optional;

import org.serratec.backend.grupo2.model.Foto;
import org.serratec.backend.grupo2.model.Usuario;
import org.serratec.backend.grupo2.repository.FotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;

@Service
public class ImageService {

//    public BufferedImage getJpgImageFromFile(MultipartFile uploadedFile) throws IOException {
//        BufferedImage img = ImageIO.read(uploadedFile.getInputStream());
//        BufferedImage jpgImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
//        jpgImage.createGraphics().drawImage(img, 0, 0, null);
//        return jpgImage;
//    }
//
//    public BufferedImage cropSquare(BufferedImage sourceImg) {
//        int min = Math.min(sourceImg.getWidth(), sourceImg.getHeight());
//        return sourceImg.getSubimage((sourceImg.getWidth() - min) / 2, (sourceImg.getHeight() - min) / 2, min, min);
//    }
//
//    public BufferedImage resize(BufferedImage sourceImg, int size) {
//        BufferedImage resizedImg = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
//        Graphics2D g = resizedImg.createGraphics();
//        g.drawImage(sourceImg, 0, 0, size, size, null);
//        g.dispose();
//        return resizedImg;
//    }
//
//    public byte[] convertImageToBytes(BufferedImage bufferedImage) throws IOException {
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
//        return byteArrayOutputStream.toByteArray();
//    }
    
	@Autowired
	private FotoRepository fotoRepository;
	
	public Foto inserir(Usuario usuario, MultipartFile file) throws IOException {
		Foto foto = new Foto();
		foto.setNome(file.getName());
		foto.setTipo(file.getContentType());
		foto.setDados(file.getBytes());
		foto.setUsuario(usuario);
		
		foto = fotoRepository.save(foto);
		
		return foto;
	}
	
	@Transactional
	public Foto buscarPorIdUsuario(Long id) {
		Usuario usuario = new Usuario();
		usuario.setId(id);
		
		Optional<Foto> fotoOpt = fotoRepository.findByUsuario(usuario);
		
		if (fotoOpt.isEmpty()) {
			return null;
		}
		return fotoOpt.get();
	}
    
    
    
}
