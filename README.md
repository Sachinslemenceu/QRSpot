## 📱 QRSpot

**QRSpot** is a modern, lightweight, and privacy-focused Android application for scanning and generating QR codes and barcodes. Built entirely in **Kotlin** using the latest **AndroidX** and **Jetpack** libraries, the app delivers fast performance, accurate scanning, and a clean Material Design user experience.

Designed with simplicity and reliability in mind, QRSpot works fully **offline** for its core functionality and processes all scans locally on the device, ensuring user privacy and data security.

---

### ✨ Overview

QRSpot provides real-time camera-based scanning with continuous autofocus for reliable detection in various lighting conditions. The app safely interprets scanned content such as URLs, plain text, and contact information (vCard), and presents users with clear, context-aware actions like opening links, copying data, or sharing results.

In addition to scanning, QRSpot includes an in-app QR code generator that allows users to create high-quality QR codes from text or URLs and instantly share or export them as images.

---

### 🚀 Key Features

- ⚡ **Fast & accurate scanning** using the device camera  
- 🔒 **Privacy-first & offline** — no network required for scanning or generation  
- 🧾 **Smart payload handling** (links, text, vCard)  
- 🖼️ **Built-in QR generator** with preview, share, and export options  
- 🎨 **Clean Material UI** built with AndroidX components  
- ⚙️ **Configurable settings** (flash control, camera selection, supported formats)  
- 🗂️ Optional **scan & generation history**

---

### 🏗 Architecture & Tech

QRSpot follows a modern and scalable **MVVM architecture**, ensuring a clean separation of concerns and long-term maintainability.

- **Language:** Kotlin  
- **UI:** Material Design + AndroidX  
- **Camera:** CameraX  
- **QR Processing:** ZXing / ML Kit  
- **Async:** Kotlin Coroutines & Flow  
- **Architecture:** MVVM (ViewModel, StateFlow / LiveData)  

Camera and decoding logic are isolated from the UI layer, improving testability and making the codebase easy to extend.

---

### 🔐 Privacy & Security

- No internet connection required for core features  
- All QR processing is performed locally on the device  
- No user data is collected or transmitted  

---

### 🎯 Use Cases

- Everyday QR and barcode scanning  
- Quickly generating QR codes for links or text  
- Offline scanning in restricted or low-connectivity environments  
- Reference implementation for modern Android development practices  

---

### 📌 Summary

QRSpot combines performance, simplicity, and privacy into a single, well-architected Android application. Whether used as a production-ready QR utility or as a learning reference for clean Android development with Kotlin and CameraX, QRSpot provides a solid and extensible foundation.

---

⭐ If you find this project useful, consider starring the repository!

---
Contact Repo owner:
Sachinslemenceu For questions or collaboration: open an issue or PR in this repository
