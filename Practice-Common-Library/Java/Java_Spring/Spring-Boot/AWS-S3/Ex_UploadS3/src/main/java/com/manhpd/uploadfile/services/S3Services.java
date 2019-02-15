/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.manhpd.uploadfile.services;

/**
 *
 * @author manh.phanduc
 */
public interface S3Services {
    
    public void downloadFile(String keyName);
    
    public void uploadFile(String keyName, String uploadFilePath);
}
