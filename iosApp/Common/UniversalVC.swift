
import Foundation
import SwiftUI
struct UniversalVC: UIViewControllerRepresentable {
    let view: () -> UIViewController
    func makeUIViewController(context: Context) -> UIViewController {
        view()
    }
    
    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
        
    }
}
